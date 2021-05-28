package tech.qvanphong.arkdc.views.arkdelegatorcalculator;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import tech.qvanphong.arkdc.services.ArkDelegatesService;

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
@PWA(display = "standalone", name = "ARK Delegate Calculator", shortName = "ARK Delegate", iconPath = "icons/favicon.png")
public class CalculatorView extends PolymerTemplate<CalculatorView.CalculatorViewModel> {

    private ArkDelegatesService delegatesService;
    @Id("switch-mode-button")
    private Button switchModeButton;
    @Id("switch-mode-button-mobile")
    private Button switchModeMobileButton;
    @Id("is-voted")
    private Checkbox isVoted;
    @Id("ark-balance")
    private NumberField arkBalance;
    private final AllDelegate allDelegate;
    private final SingleDelegateCalculator singleDelegateCalculator;
    private final String SW_TABLE_STR = "Switch to table mode";
    private final String SW_SINGLE_STR = "Switch to single mode";

    @Autowired
    public CalculatorView(ArkDelegatesService delegatesService) {
        this.delegatesService = delegatesService;
        this.allDelegate = new AllDelegate(delegatesService);
        this.singleDelegateCalculator = new SingleDelegateCalculator(delegatesService);
        this.arkBalance.addValueChangeListener(e -> valueChangedListener());
        this.isVoted.addValueChangeListener(e -> valueChangedListener());

        switchModeButton.setIcon(VaadinIcon.REFRESH.create());
        switchModeMobileButton.setIcon(VaadinIcon.REFRESH.create());
        switchModeButton.addClickListener(e -> switchModeListener());
        switchModeMobileButton.addClickListener(e -> switchModeListener());

        Element element = allDelegate.getElement();

        getElement().appendChild(element);
    }

    private void valueChangedListener() {
        allDelegate.setArkBalance(arkBalance.getValue());
        allDelegate.setVoted(isVoted.getValue());
        singleDelegateCalculator.setArkBalance(arkBalance.getValue());
        singleDelegateCalculator.setVoted(isVoted.getValue());

        if (getElement().getChild(0).equals(allDelegate.getElement())) {
            allDelegate.refreshOnValueChange();
        } else {
            singleDelegateCalculator.updateCalculateResult();
        }
    }

    private void switchModeListener() {
        Element elementToReplace;
        if (getElement().getChild(0).equals(allDelegate.getElement())) {
            elementToReplace = singleDelegateCalculator.getElement();
            switchModeButton.setText(SW_TABLE_STR);
            switchModeMobileButton.setText(SW_TABLE_STR);
        } else {
            elementToReplace = allDelegate.getElement();
            switchModeButton.setText(SW_SINGLE_STR);
            switchModeMobileButton.setText(SW_SINGLE_STR);
        }
        valueChangedListener();
        getElement().removeAllChildren();
        getElement().appendChild(elementToReplace);
    }

    public interface CalculatorViewModel extends TemplateModel {
    }
}
