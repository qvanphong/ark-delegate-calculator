package tech.qvanphong.arkdc.views.arkdelegatorcalculator;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import tech.qvanphong.arkdc.models.Datum;
import tech.qvanphong.arkdc.services.ArkDelegatesService;

@Tag("calculator-view")
@JsModule("./views/calculator/calculator-view.js")
@Route("")
@PageTitle("ARK Delegate Calculator")
@Viewport("width=device-width, initial-scale=1.0")
@PWA(display = "standalone", name = "ARK Delegate Calculator", shortName = "ARK Delegate", iconPath = "icons/favicon.png")
public class CalculatorView extends PolymerTemplate<CalculatorView.CalculatorViewModel> {

    @Id("ark-balance")
    private NumberField arkBalance;
    @Id("is-voted")
    private Checkbox isVoted;
    @Id("delegate-list")
    private ComboBox<Datum> delegateList;

    private ArkDelegatesService delegatesService;
    private Datum selectedDelegate;


    @Autowired
    public CalculatorView(ArkDelegatesService delegatesService) {
        this.delegatesService = delegatesService;
        initDelegateListComboBox();
        arkBalance.addValueChangeListener(event -> updateCalculateUI());
        isVoted.addValueChangeListener(event -> updateCalculateUI());
    }

    private void initDelegateListComboBox() {
        WebBrowser browser = VaadinSession.getCurrent().getBrowser();
        delegateList.setItemLabelGenerator(Datum::getName);
        delegateList.setItems(delegatesService.get51Delegates());
        delegateList.addValueChangeListener(event -> {
            this.selectedDelegate = event.getValue();
            updateCalculateUI();
            updateDelegateInformationUI(event.getValue());
        });
        // Alternative way because there is a bug on Responsive Step of Form Layout when wrapped inside ComboBox
        delegateList.setRenderer(browser.isAndroid() || browser.isIPhone() || browser.isWindowsPhone() ?
                createMobileDelegateListComboBox() : createDesktopDelegateListComboBox());
    }

    private ComponentRenderer<FormLayout, Datum> createDesktopDelegateListComboBox() {
        return new ComponentRenderer<>(delegate -> {
            String formattedWeight = String.format("%.2f", Long.parseLong(delegate.getDelegateStatistics().getVoting_power()) / 100000000F);

            Element subRanking = new Html("<sup style='color: var(--lumo-error-color)'>" + delegate.getRank() + "</sup>").getElement();
            Span delegateNameWithRank = new Span(delegate.getName());
            delegateNameWithRank.getElement().appendChild(subRanking);
            Span payout = new Span(String.valueOf(delegate.getPayout_percent()));
            Span voteWeight = new Span(formattedWeight);

            delegateNameWithRank.setId("content-s");
            payout.setId("content-s");
            voteWeight.setId("content-s");

            FormLayout formLayout = new FormLayout();
            formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0px", 3, FormLayout.ResponsiveStep.LabelsPosition.TOP));
            formLayout.addFormItem(delegateNameWithRank, "Name");
            formLayout.addFormItem(payout, "Shares");
            formLayout.addFormItem(voteWeight, "Vote weight");

            return formLayout;
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


    private void updateDelegateInformationUI(Datum delegate) {
        String votingPowerStr = delegate.getDelegateStatistics().getVoting_power();
        String minPayStr = delegate.getPayout_minimum();
        double votingWeight = votingPowerStr == null || votingPowerStr.isEmpty() ? 0 : Double.parseDouble(votingPowerStr) / 100000000;
        votingWeight = Math.round(votingWeight);
        double minPay = minPayStr == null || minPayStr.isEmpty() ? 0 : Double.parseDouble(minPayStr) / 100000000;
        getModel().setDelegateName(delegate.getName());
        getModel().setDelegateRank(delegate.getRank());
        getModel().setDelegateVoter(delegate.getDelegateStatistics().getVoters());
        getModel().setDelegateVoteWeight(votingWeight);
        getModel().setDelegateMinPay(minPay);
        getModel().setDelegatePayInterval(delegate.getPayout_interval() + "h");
        getModel().setDelegateWebsite(delegate.getWebsite());
        getModel().setDelegateDetailLink(delegatesService.getDelegateLiveUrl() + "delegate/" + delegate.getSlug());
        String icon = delegate.getContribution_status().equals("inactive") ?
                "vaadin:ellipsis-circle" : delegate.getContribution_status().equals("active") ? "check-circle" : "vaadin:close-circle";
        getModel().setContributeIcon("vaadin:" + icon);
        getModel().setContributeClass(delegate.getContribution_status());
    }

    private void updateCalculateUI() {
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

    public interface CalculatorViewModel extends TemplateModel {
        void setArkPerDay(double value);

        void setArkPerWeek(double value);

        void setArkPerMonth(double value);

        void setArkPerYear(double value);

        void setDelegateName(String value);

        void setDelegateRank(int value);

        void setDelegateVoter(double value);

        void setDelegateVoteWeight(double value);

        void setDelegateMinPay(double value);

        void setDelegatePayInterval(String value);

        void setContributeIcon(String value);

        void setDelegateWebsite(String value);

        void setDelegateDetailLink(String value);

        void setContributeClass(String value);
    }
}
