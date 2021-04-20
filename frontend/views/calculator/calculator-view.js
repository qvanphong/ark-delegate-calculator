import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-text-field/src/vaadin-number-field.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-layout.js';
import '@vaadin/vaadin-checkbox/src/vaadin-checkbox.js';
import '@vaadin/vaadin-grid/src/vaadin-grid.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-item.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';

class CalculatorView extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
    
                .bold {
                    font-weight: bold;
                }
    
                .fake-ark-icon {
                    color: var(--lumo-error-color);
                }
    
                #notification-text{
                    font-size: var(--lumo-font-size-xs);
                    text-decoration: italic;
                }
            </style>
<vaadin-horizontal-layout class="content" style="width: 100%; height: 100%;">
 <vaadin-vertical-layout theme="spacing" style="flex-grow: 1; width: 60%; margin: var(--lumo-space-m);">
  <h2>ARK Delegates Selector</h2>
  <vaadin-grid id="delegate-grid"></vaadin-grid>
 </vaadin-vertical-layout>
 <vaadin-vertical-layout theme="spacing" style="flex-grow: 0; width: 40%; margin: var(--lumo-space-m);">
  <vaadin-vertical-layout theme="spacing" style="width: 100%;">
   <h2 style="margin-bottom: var(--lumo-space-xs);">ARK Delegate Calculator</h2>
   <vaadin-horizontal-layout style="width: 100%; align-items: flex-end;" theme="spacing-xl">
    <vaadin-number-field style="flex-grow: 1;" label="ARK Balance:" id="ark-balance">
     <span slot="prefix" class="bold">ARK</span>
     <span slot="suffix" class="bold">Ѧ</span>
    </vaadin-number-field>
    <vaadin-checkbox style="flex-grow: 1;" id="is-voted">
      I voted this delegate 
    </vaadin-checkbox>
   </vaadin-horizontal-layout>
   <i id="notification-text">{{notification}}</i>
  </vaadin-vertical-layout>
  <vaadin-vertical-layout style="margin-top: var(--lumo-space-xl); width: 100%;">
   <h2 style="flex-shrink: 0;">Profit</h2>
   <vaadin-form-layout style="width: 100%;" id="profit-form">
    <vaadin-form-item>
     <label slot="label">ARK/Day</label>
     <label>~</label>
     <label class="bold">{{arkPerDay}}</label>
     <label class="bold fake-ark-icon">Ѧ</label>
    </vaadin-form-item>
    <vaadin-form-item>
     <label slot="label">ARK/Week</label>
     <label>~</label>
     <label class="bold">{{arkPerWeek}}</label>
     <label class="bold fake-ark-icon">Ѧ</label>
    </vaadin-form-item>
    <vaadin-form-item>
     <label slot="label">ARK/Month</label>
     <label>~</label>
     <label class="bold">{{arkPerMonth}}</label>
     <label class="bold fake-ark-icon">Ѧ</label>
    </vaadin-form-item>
    <vaadin-form-item>
     <label slot="label">ARK/Year</label>
     <label>~</label>
     <label class="bold">{{arkPerYear}} </label>
     <label class="bold fake-ark-icon">Ѧ</label>
    </vaadin-form-item>
   </vaadin-form-layout>
  </vaadin-vertical-layout>
  <vaadin-vertical-layout theme="spacing" style="width: 100%; margin-top: auto;">
   <i style="flex-shrink: 0; flex-grow: 0; align-self: center; font-size: var(--lumo-font-size-xs)">By<a href="Phong#2159">Phong#2159</a>with ❤, check this<a href="https://vaadin.com">github project.</a></i>
  </vaadin-vertical-layout>
 </vaadin-vertical-layout>
</vaadin-horizontal-layout>
<!-- Oh you found me. Find me on TLLN ;) -->
`;
    }

    static get is() {
        return 'calculator-view';
    }

    static get properties() {
        return {
            notification: {
                type: String,
                value: 'Input your ARK balance then select which delegate you want to stake with.',
            },
            arkPerDay: Number,
            arkPerMonth: Number,
            arkPerYear: Number,
            arkPerWeek: Number,
        };
    }

    ready() {
        super.ready();
        // const calculateForm = this.$['calculate-form'];
        const profitForm = this.$['profit-form'];
        const formResponsiveSteps = [{minWidth: 0, columns: 1}];

        // calculateForm.responsiveSteps = formResponsiveSteps;
        profitForm.responsiveSteps = formResponsiveSteps;
    }
}

customElements.define(CalculatorView.is, CalculatorView);
