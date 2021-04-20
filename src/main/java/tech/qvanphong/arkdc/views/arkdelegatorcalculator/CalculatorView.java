package tech.qvanphong.arkdc.views.arkdelegatorcalculator;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
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
@Viewport("width=device-width, initial-scale=1.0")
public class CalculatorView extends PolymerTemplate<CalculatorView.CalculatorViewModel> implements AfterNavigationObserver {

    @Id("ark-balance")
    private NumberField arkBalance;
    @Id("is-voted")
    private Checkbox isVoted;
    @Id("delegate-list")
    private ComboBox<Datum> delegateList;

    private ArkDelegatesAPI delegatesAPI;
    private Datum selectedDelegate;


    @Autowired
    public CalculatorView(ArkDelegatesAPI delegatesAPI) {
        this.delegatesAPI = delegatesAPI;
        initDelegateListComboBox();
        arkBalance.addValueChangeListener(event -> calculate());
        isVoted.addValueChangeListener(event -> calculate());
    }

    private void initDelegateListComboBox() {
        WebBrowser browser = VaadinSession.getCurrent().getBrowser();
        delegateList.setItemLabelGenerator(Datum::getName);
        delegateList.addValueChangeListener(event -> {
            this.selectedDelegate = event.getValue();
            calculate();
        });
        delegateList.setRenderer(browser.isAndroid() || browser.isIPhone() || browser.isWindowsPhone() ?
                createMobileDelegateListComboBox() : createDesktopDelegateListComboBox());
    }

    private ComponentRenderer<HorizontalLayout, Datum> createDesktopDelegateListComboBox() {
        return new ComponentRenderer<>(delegate -> {
            HorizontalLayout container = new HorizontalLayout();
            Span delegateNameWithRank = new Span(delegate.getName());
            Element subRanking = new Html("<sup style='color: var(--lumo-error-color)'>" + delegate.getRank() + "</sup>").getElement();
            delegateNameWithRank.getElement().appendChild(subRanking);

            Component delegateName = createCellContent("Name", delegateNameWithRank);
            Component shares = createCellContent("Shares", delegate.getPayout_percent());
            String formattedWeight = String.format("%.2f", Long.parseLong(delegate.getDelegateStatistics().getVoting_power()) / 100000000F);
            Component voteWeight = createCellContent("Vote weight", formattedWeight);

            container.add(delegateName, shares, voteWeight);
            container.setFlexGrow(1, delegateName);
            container.setFlexGrow(0, shares);
            container.setFlexGrow(1, voteWeight);
            return container;
        });
    }

    private ComponentRenderer<FormLayout, Datum> createMobileDelegateListComboBox() {
        return new ComponentRenderer<>(delegate -> {
            String formattedWeight = String.format("%.2f", Long.parseLong(delegate.getDelegateStatistics().getVoting_power()) / 100000000F);
            FormLayout formLayout = new FormLayout();
            Span delegateName = new Span(delegate.getName());
            Element subRanking = new Html("<sup style='color: var(--lumo-error-color)'>" + delegate.getRank() + "</sup>").getElement();
            delegateName.getElement().appendChild(subRanking);

            formLayout.addFormItem(delegateName, "Name: ");
            formLayout.addFormItem(new Span(delegate.getPayout_percent() + "%"), "Share: ");
            formLayout.addFormItem(new Span(formattedWeight), "Vote: ");
            formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0px", 1, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
            formLayout.add(new Hr());

            return formLayout;
        });
    }

    private void calculate() {
        if (!arkBalance.isEmpty() && this.selectedDelegate != null) {
            boolean isVotedForDelegate = isVoted.getValue();
            double arkShare = (float) (422 * selectedDelegate.getPayout_percent() / 100);
            String votingWeightStr = this.selectedDelegate.getDelegateStatistics().getVoting_power();
            long convertedVotingWeightNumber = votingWeightStr == null || votingWeightStr.isEmpty() ? 0 : Long.parseLong(votingWeightStr);
            double votingWeight = Math.round(convertedVotingWeightNumber / 100000000F);
            double inDelegateShare = (votingWeight + (isVotedForDelegate ? 0 : arkBalance.getValue())) / arkBalance.getValue();

            getModel().setArkPerDay(arkShare / inDelegateShare);
            getModel().setArkPerWeek(arkShare / inDelegateShare * 7);
            getModel().setArkPerMonth(arkShare / inDelegateShare * 30);
            getModel().setArkPerYear(arkShare / inDelegateShare * 365);
        }
    }

    private Component createCellContent(String title, Object content) {
        return createCellContent(title, content, null);
    }

    private Component createCellContent(String title, Object content, String width) {
        Span shares = new Span(title);
        shares.getElement().getStyle().set("font-weight", "500");
        Component contentComponent = content == null ?
                new Span() :
                (content instanceof Component) ? (Component) content : new Span(content.toString());

        VerticalLayout container = new VerticalLayout();
        if (width != null) container.setWidth(width);
        container.setSpacing(false);
//        container.setAlignItems(FlexComponent.Alignment.CENTER);
        container.getThemeList().add("spacing-xs");
        container.add(shares, contentComponent);
        return container;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        delegateList.setItems(delegatesAPI.get51Delegates());
    }

    public interface CalculatorViewModel extends TemplateModel {
        void setArkPerDay(double value);

        void setArkPerWeek(double value);

        void setArkPerMonth(double value);

        void setArkPerYear(double value);

    }
}
