package tech.qvanphong.arkdc.views.arkdelegatorcalculator;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import tech.qvanphong.arkdc.models.Datum;
import tech.qvanphong.arkdc.services.ArkDelegatesAPI;

/**
 * A Designer generated component for the calculator-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("calculator-view")
@JsModule("./views/calculator/calculator-view.js")
@Route("")
@PageTitle("ARK Delegate Calculator")
public class CalculatorView extends PolymerTemplate<CalculatorView.CalculatorViewModel> implements AfterNavigationObserver {

    @Id("delegate-grid")
    private Grid<Datum> delegateGrid;
    @Id("ark-balance")
    private NumberField arkBalance;
    @Id("is-voted")
    private Checkbox isVoted;

    private ArkDelegatesAPI delegatesAPI;
    private Datum selectedDelegate;

    @Autowired
    public CalculatorView(ArkDelegatesAPI delegatesAPI) {
        this.delegatesAPI = delegatesAPI;
        initGrid();
        arkBalance.addValueChangeListener(event -> calculate());
        isVoted.addValueChangeListener(event -> calculate());


    }

    private void initGrid() {
        delegateGrid.addComponentColumn(datum -> {
            Html ranking = new Html("<sup style='color: var(--lumo-error-color)'>" + datum.getRank() + "</sup>");
            Span name = new Span(datum.getName());
            name.getElement().getStyle().set("font-weight", "500");
            name.getElement().appendChild(ranking.getElement());


            Span status = new Span(datum.getIs_private() ? "Private" : "Public");
            status.getElement().getStyle().set("font-size", "var(--lumo-font-size-s)");

            VerticalLayout container = new VerticalLayout();
            container.setSpacing(false);
            container.add(name, status);
            return container;
        }).setFlexGrow(1);

        delegateGrid.addComponentColumn(datum -> createCellContent("Contributes", createContributeIcon(datum.getContribution_status()))).setAutoWidth(false).setFlexGrow(0);

        delegateGrid.addComponentColumn(datum -> createCellContent("Shares", datum.getPayout_percent() + "%")).setFlexGrow(0);

        delegateGrid.addComponentColumn(datum -> {
            String formatNumber = String.format("%d", datum.getDelegateStatistics().getVoters());
            return createCellContent("Voters", formatNumber);
        }).setFlexGrow(0);

        delegateGrid.addComponentColumn(datum -> {
            String votingWeight = datum.getDelegateStatistics().getVoting_power();
            long convertedNumber = votingWeight == null || votingWeight.isEmpty() ? 0 : Long.parseLong(votingWeight);
            String formatNumber = String.format("%d", Math.round(convertedNumber/ 100000000F));

            return createCellContent("Vote weight", formatNumber);
        }).setFlexGrow(1);

        delegateGrid.addComponentColumn(datum -> createCellContent("Payout interval", datum.getPayout_interval() + "h")).setFlexGrow(1);

        delegateGrid.addComponentColumn(datum -> {
            String payoutMinimum = datum.getPayout_minimum();
            int convertedNumber = 0;
            try {
                convertedNumber = payoutMinimum == null || payoutMinimum.isEmpty() ? 0 : Integer.parseInt(payoutMinimum);
            } catch (Exception ignore) {}

            String formatNumber = String.format("%f Ѧ", convertedNumber / 100000000F);

            return createCellContent("Min pay", formatNumber);
        }).setFlexGrow(0);

        delegateGrid.addComponentColumn(datum -> new Button("Select", click -> {
            this.selectedDelegate = datum;
            calculate();
        }));
    }

    private void calculate(){
        if(!arkBalance.isEmpty() && this.selectedDelegate != null){
            boolean isVotedForDelegate = isVoted.getValue();
            double arkShare = (float) (422 * 0.75);
            String votingWeightStr = this.selectedDelegate.getDelegateStatistics().getVoting_power();
            long convertedNumber = votingWeightStr == null || votingWeightStr.isEmpty() ? 0 : Long.parseLong(votingWeightStr);
            double votingWeight = Math.round(convertedNumber/ 100000000F);
            double inDelegateShare = (votingWeight + (isVotedForDelegate ? 0 : arkBalance.getValue())) / arkBalance.getValue();

            getModel().setArkPerDay(arkShare / inDelegateShare);
            getModel().setArkPerWeek(arkShare / inDelegateShare * 7);
            getModel().setArkPerMonth(arkShare / inDelegateShare * 30);
            getModel().setArkPerYear(arkShare / inDelegateShare * 365);
        }
        invokeNotificationCheck();
    }
    
    private void invokeNotificationCheck() {
        if(!arkBalance.getValue().isNaN() && selectedDelegate == null) {
            getModel().setNotification("👈 select delegate here.");
        } else if (selectedDelegate != null && arkBalance.getValue().isNaN()) {
            getModel().setNotification("☝ input your ARK balance.");
        } else {
            getModel().setNotification("👌 selecting delegate: " + selectedDelegate.getName());
        }
    } 

    private Component createCellContent(String title, Object content) {
        Span shares = new Span(title);
        shares.getElement().getStyle().set("font-weight", "500");
        Component contentComponent = content == null ?
                new Span() :
                (content instanceof Component) ? (Component) content : new Span(content.toString());

        VerticalLayout container = new VerticalLayout();
        container.setSpacing(false);
        container.setAlignItems(FlexComponent.Alignment.CENTER);
        container.getThemeList().add("spacing-xs");
        container.add(shares, contentComponent);
        return container;
    }

    private Icon createContributeIcon(String contributeStatus) {
        Icon icon = null;
        switch (contributeStatus) {
            case "never":
                icon = VaadinIcon.CLOSE_CIRCLE.create();
                icon.setColor("var(--lumo-error-color)");
                break;
            case "inactive":
                icon = VaadinIcon.ELLIPSIS_CIRCLE.create();
                icon.setColor("var(--lumo-error-color)");
                break;
            case "active":
                icon = VaadinIcon.CHECK_CIRCLE.create();
                icon.setColor("var(--lumo-success-color)");
                break;
            case "semi-active":
                icon = VaadinIcon.CHECK_CIRCLE.create();
                icon.setColor("#f6ad55");
                break;
        }

        return icon;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        delegateGrid.setItems(delegatesAPI.get51Delegates());
    }

    public interface CalculatorViewModel extends TemplateModel {
        void setArkPerDay(double value);
        void setArkPerWeek(double value);
        void setArkPerMonth(double value);
        void setArkPerYear(double value);
        void setNotification(String value);
    }
}
