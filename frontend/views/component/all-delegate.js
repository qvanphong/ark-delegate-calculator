import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-grid/src/vaadin-grid.js';
import '@vaadin/vaadin-text-field/src/vaadin-number-field.js';
import '@vaadin/vaadin-checkbox/src/vaadin-checkbox.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '../../styles/shared-styles.js';
import '@vaadin/vaadin-accordion/src/vaadin-accordion.js';
import '@vaadin/vaadin-accordion/src/vaadin-accordion-panel.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-layout.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-item.js';
import '@polymer/iron-icon/iron-icon.js';

class AllDelegate extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                    width: 100%;
                }


                .bold {
                    font-weight: bold;
                }

                .combobox-cell-mobile {
                    font-size: var(--lumo-font-size-xs);
                }

                .fake-ark-icon {
                    color: var(--lumo-error-color);
                }

                #notification-text {
                    font-size: var(--lumo-font-size-xs);
                    text-decoration: italic;
                }

                #delegate-grid {
                    height: 100%;
                }

                .delegate-link {
                    text-decoration: none;   
                    font-weight: 500;
                    color: #037cff;
                }
                
                .delegate-link:hover {
                    text-decoration: underline;
                }
                
                .delegate-status-container > *{
                    font-size: var(--lumo-font-size-s);
                    font-weight: 500;
                }
                
                .delegate-contribute {
                    border-left: 1px rgba(24, 39, 57, 0.4) solid;
                    padding-left: var(--lumo-space-xs);
                    margin-left: var(--lumo-space-xs)
                }

                .inactive {
                    color: #ffc13f;
                }

                .never {
                    color: red;
                }

                .active {
                    color: #2ca84f;
                }

                .icon {
                    color: grey;
                }
            </style>
 <vaadin-grid id="delegate-grid"></vaadin-grid>
`;
    }

    static get is() {
        return 'all-delegate';
    }

    static get properties() {
        return {};
    }
}

customElements.define(AllDelegate.is, AllDelegate);
