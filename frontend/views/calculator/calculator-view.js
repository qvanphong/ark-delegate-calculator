import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@vaadin/vaadin-text-field/src/vaadin-number-field.js';
import '@vaadin/vaadin-checkbox/src/vaadin-checkbox.js';

class CalculatorView extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                    font-family: var(--lumo-font-family);
                }
    
                #ark-viet-link {
                    text-decoration: none;
                    font-weight: 500;
                    color: var(--lumo-contrast);
                    margin-left: auto;
                }
    
                @media screen and (min-width: 770px) {
                    #container {
                        width: 50%;
                    }

                    #switch-mode-button {
                        display: block;
                    }

                    #switch-mode-button-mobile {
                        display: none;
                    }
                }

                @media screen and (max-width: 769px) {
                    #container {
                        width: 100%
                    }
                    
                    #switch-mode-button {
                        display: none;
                    }
                    
                    #switch-mode-button-mobile {
                        display: block;
                    }
                }
            </style>
<vaadin-horizontal-layout class="content" style="width: 100%; height: 100%; justify-content: center;">
 <vaadin-vertical-layout theme="spacing" style="display: block; height: 100%;padding: var(--lumo-space-s)" id="container">
  <vaadin-vertical-layout style="width: 100%" theme="spacing">
   <vaadin-horizontal-layout theme="spacing" style="align-items: center; width: 100%">
    <img style="width: auto; height: 38px;" src="https://ark.io/images/logo.svg">
    <h2 style="margin-bottom: var(--lumo-space-xs); margin: 0; padding: 0; margin-left: var(--lumo-space-m);"> ARK Delegate Calculator</h2>
    <vaadin-button  id="switch-mode-button">Switch to single mode 
    </vaadin-button>
       <a id="ark-viet-link" href="https://arkviet.com/">Arkviet.com</a>
   </vaadin-horizontal-layout>
      <vaadin-button  id="switch-mode-button-mobile">Switch to single mode
      </vaadin-button>
   <vaadin-horizontal-layout style="width: 100%; align-items: flex-end;" theme="spacing">
    <vaadin-number-field style="flex-grow: 1;" label="ARK Balance:" id="ark-balance">
     <span slot="suffix" class="bold">Ѧ</span>
    </vaadin-number-field>
    <vaadin-checkbox style="flex-grow: 0;margin-bottom: var(--lumo-space-s);" id="is-voted">
      Voted 
    </vaadin-checkbox>
   </vaadin-horizontal-layout>
  </vaadin-vertical-layout>
  <slot style="width: 100%;"></slot>
 </vaadin-vertical-layout>
</vaadin-horizontal-layout>
`;
    }

    static get is() {
        return 'calculator-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(CalculatorView.is, CalculatorView);
