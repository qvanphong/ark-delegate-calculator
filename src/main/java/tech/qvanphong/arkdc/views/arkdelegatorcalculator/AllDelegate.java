package tech.qvanphong.arkdc.views.arkdelegatorcalculator;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import tech.qvanphong.arkdc.models.Datum;
import tech.qvanphong.arkdc.services.ArkDelegatesService;
import tech.qvanphong.arkdc.utils.StakeCalculator;

/**
 * A Designer generated component for the all-delegate template.
 * <p>
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("all-delegate")
@JsModule("./views/component/all-delegate.js")
public class AllDelegate extends PolymerTemplate<AllDelegate.AllDelegateModel> {

    @Id("delegate-grid")
    private Grid<Datum> delegateGrid;
    Double arkBalance;
    boolean isVoted;

    private ArkDelegatesService delegatesService;

    public AllDelegate(ArkDelegatesService delegatesService) {
        this.delegatesService = delegatesService;
        initGrid();
        delegateGrid.setItems(delegatesService.get51Delegates());
    }

    private void initGrid() {
        delegateGrid.addComponentColumn(datum -> {
            VerticalLayout container = new VerticalLayout();
            Anchor delegateName = new Anchor(delegatesService.getDelegateDetailUrl() + datum.getSlug(), datum.getName());
            delegateName.setClassName("delegate-link");

            HorizontalLayout statusContainer = new HorizontalLayout();
            statusContainer.setClassName("delegate-status-container");
            statusContainer.setSpacing(false);
            Span claimStatus = new Span(datum.getIs_claimed() ?
                    (datum.getIs_private() ? "Private" : "Public") : "Unclaimed");
            statusContainer.add(claimStatus);

            if (!(claimStatus.equals("Unclaimed") || datum.getContribution_status().equals("never"))) {
                Span contributeStatus = new Span(datum.getContribution_status());
                contributeStatus.setClassName("delegate-contribute");
                statusContainer.add(contributeStatus);
            }

            container.setSpacing(false);
            container.add(delegateName, statusContainer);
            return container;
        }).setHeader("Name")
        .setAutoWidth(true)
        .setFlexGrow(1);
        delegateGrid.addComponentColumn(datum -> {
            if (arkBalance == null || arkBalance == 0) {
                return new Span();
            }
            String rewardAmount = String.format("%.5f", StakeCalculator.calculateStake(arkBalance, datum.getPayout_percent(), datum.getDelegateStatistics().getVoting_power(), isVoted));

            return new Html("<span>" + rewardAmount + " <label class=\"bold fake-ark-icon\">Ѧ</label></span>");
        }).setHeader("Daily Rewards")
                .setAutoWidth(true)
                .setFlexGrow(1);

        delegateGrid.addComponentColumn(datum -> {
            if (arkBalance == null || arkBalance == 0) {
                return new Span();
            }
            String rewardAmount = String.format("%.5f", StakeCalculator.calculateStake(arkBalance, datum.getPayout_percent(), datum.getDelegateStatistics().getVoting_power(), isVoted) * 7);

            return new Html("<span>" + rewardAmount + " <label class=\"bold fake-ark-icon\">Ѧ</label></span>");
        }).setHeader("Weekly Rewards")
                .setAutoWidth(true)
                .setFlexGrow(1);

        delegateGrid.addColumn(datum -> datum.getPayout_percent() + "%")
                .setHeader("Payout");

        delegateGrid.addColumn(datum -> datum.getPayout_interval() + "h")
                .setHeader("Payout");
    }

    public Double getArkBalance() {
        return arkBalance;
    }

    public void setArkBalance(Double arkBalance) {
        this.arkBalance = arkBalance;
    }

    public boolean isVoted() {
        return isVoted;
    }

    public void setVoted(boolean voted) {
        isVoted = voted;
    }

    public void refreshOnValueChange() {
        delegateGrid.getDataProvider().refreshAll();
    }


    public interface AllDelegateModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }

}
