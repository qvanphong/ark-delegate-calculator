import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-text-field/src/vaadin-number-field.js';
import '@vaadin/vaadin-checkbox/src/vaadin-checkbox.js';
import '@vaadin/vaadin-grid/src/vaadin-grid.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-combo-box/src/vaadin-combo-box.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '../../styles/shared-styles.js';
import '@vaadin/vaadin-accordion/src/vaadin-accordion.js';
import '@vaadin/vaadin-accordion/src/vaadin-accordion-panel.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-layout.js';
import '@vaadin/vaadin-form-layout/src/vaadin-form-item.js';
import '@polymer/iron-icon/iron-icon.js';

class CalculatorView extends PolymerElement {

    static get template() {
        return html`
            <style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }

                @media only screen and (min-width: 0px) {
                    #container {
                        width: 90%;
                    }
                }
                
                /*Fit to ipad screen*/
                @media only screen and (min-width: 769px) {
                    #container {
                        width: 35%;
                    }
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
            <vaadin-horizontal-layout class="content" style="width: 100%; height: 100%; justify-content: center;">
                <vaadin-vertical-layout theme="spacing"
                                        style="flex-grow: 0; margin: var(--lumo-space-m); flex-shrink: 0; flex-direction: column;"
                                        id="container">
                    <vaadin-vertical-layout theme="spacing" style="width: 100%;">
                        <vaadin-horizontal-layout theme="spacing" style="align-items: center;">
                            <img style="width: auto; height: 38px;" src="https://ark.io/images/logo.svg">
                            <h2 style="margin-bottom: var(--lumo-space-xs); margin: 0; padding: 0; margin-left: var(--lumo-space-m);">
                                ARK Delegate Calculator</h2>
                        </vaadin-horizontal-layout>
                        <vaadin-horizontal-layout style="width: 100%; align-items: flex-end;" theme="spacing-xl">
                            <vaadin-number-field style="flex-grow: 1;" label="ARK Balance:" id="ark-balance">
                                <span slot="suffix" class="bold">Ѧ</span>
                            </vaadin-number-field>
                            <vaadin-checkbox style="flex-grow: 0;" id="is-voted">
                                Voted
                            </vaadin-checkbox>
                        </vaadin-horizontal-layout>
                        <vaadin-combo-box id="delegate-list" style="width: 100%;" label="Delegate"></vaadin-combo-box>
                    </vaadin-vertical-layout>
                    <vaadin-vertical-layout style="margin-top: var(--lumo-space-xl); width: 100%;">
                        <h2 style="flex-shrink: 0;">Profit</h2>
                        <vaadin-form-layout id="profit-form" style="flex-shrink: 0;"
                                            responsive-steps="[{&quot;minWidth&quot;: 0, &quot;columns&quot;: 1}]">
                            <vaadin-form-item>
                                <label class="bold" slot="label">ARK/Day</label>
                                <label>~</label>
                                <label class="bold">{{arkPerDay}}</label>
                                <label class="bold fake-ark-icon">Ѧ</label>
                            </vaadin-form-item>
                            <vaadin-form-item>
                                <label class="bold" slot="label">ARK/Week</label>
                                <label>~</label>
                                <label class="bold">{{arkPerWeek}}</label>
                                <label class="bold fake-ark-icon">Ѧ</label>
                            </vaadin-form-item>
                            <vaadin-form-item>
                                <label class="bold" slot="label">ARK/Month</label>
                                <label>~</label>
                                <label class="bold">{{arkPerMonth}}</label>
                                <label class="bold fake-ark-icon">Ѧ</label>
                            </vaadin-form-item>
                            <vaadin-form-item>
                                <label class="bold" slot="label">ARK/Year</label>
                                <label>~</label>
                                <label class="bold">{{arkPerYear}} </label>
                                <label class="bold fake-ark-icon">Ѧ</label>
                            </vaadin-form-item>
                        </vaadin-form-layout>
                    </vaadin-vertical-layout>
                    <vaadin-vertical-layout theme="spacing" style="width: 100%; margin-top: var(--lumo-space-xl);">
                        <vaadin-accordion style="width: 100%;" opened="true">
                            <vaadin-accordion-panel style="width: 100%;" responsive-steps="[
 {&quot;minWidth&quot;: 0, &quot;columns&quot;: 1}
]" theme="filled" opened>
                                <div slot="summary">
                                    <span style="flex-shrink: 0;">Delegate Information</span>
                                </div>
                                <vaadin-form-layout id="profit-form" style="flex-shrink: 0;">
                                    <vaadin-form-item>
                                        <label class="bold" slot="label">Delegate Name</label>
                                        <label>{{delegateName}}</label>
                                    </vaadin-form-item>
                                    <vaadin-form-item>
                                        <label class="bold" slot="label">Delegate Rank</label>
                                        <label>{{delegateRank}}</label>
                                    </vaadin-form-item>
                                    <vaadin-form-item>
                                        <label class="bold" slot="label">Voters</label>
                                        <label>{{delegateVoter}}</label>
                                    </vaadin-form-item>
                                    <vaadin-form-item>
                                        <label class="bold" slot="label">Voting Weight</label>
                                        <label>{{delegateVoteWeight}}</label>
                                        <label class="bold fake-ark-icon">Ѧ</label>
                                    </vaadin-form-item>
                                    <vaadin-form-item>
                                        <label class="bold" slot="label">Min Payout</label>
                                        <label>{{delegateMinPay}}</label>
                                        <label class="bold fake-ark-icon">Ѧ</label>
                                    </vaadin-form-item>
                                    <vaadin-form-item>
                                        <label class="bold" slot="label">Payout Interval</label>
                                        <label>{{delegatePayInterval}}</label>
                                    </vaadin-form-item>
                                    <vaadin-form-item>
                                        <label class="bold" slot="label">Contributes</label>
                                        <iron-icon class$="{{contributeClass}}" icon="{{contributeIcon}}"></iron-icon>
                                    </vaadin-form-item>
                                    <vaadin-form-item>
                                        <label class="bold" slot="label">Website</label>
                                        <a href="{{delegateWebsite}}" target="_blank"><label>{{delegateWebsite}}</label></a>
                                    </vaadin-form-item>
                                    <vaadin-form-item>
                                        <label class="bold" slot="label">More Detail</label>
                                        <a href="{{delegateDetailLink}}"
                                           target="_blank"><label>{{delegateDetailLink}}</label></a>
                                    </vaadin-form-item>
                                </vaadin-form-layout>
                            </vaadin-accordion-panel>
                        </vaadin-accordion>
                    </vaadin-vertical-layout>
                    <vaadin-vertical-layout theme="spacing" style="width: 100%; margin-top: auto;">
                        <i style="flex-shrink: 0; flex-grow: 0; align-self: center; font-size: var(--lumo-font-size-xs)">By Phong#2159 (Discord) with ❤, check out this <a
                                href="https://github.com/qvanphong/ark-delegate-calculator">github project</a>. <br> 💰 Donate me: AeeNLVJBXsSYkENmFQVQSemX45q71y5fCr (ARK)</i>
                    </vaadin-vertical-layout>
                </vaadin-vertical-layout>
            </vaadin-horizontal-layout>
            <!-- Find me on TLLN ;) -->
        `;
    }

    static get is() {
        return 'calculator-view';
    }

    static get properties() {
        return {
            arkPerDay: Number,
            arkPerMonth: Number,
            arkPerYear: Number,
            arkPerWeek: Number,
        };
    }

    ready() {
        super.ready();
        const profitForm = this.$['profit-form'];
        const formResponsiveSteps = [{minWidth: 0, columns: 1}];
        profitForm.responsiveSteps = formResponsiveSteps;
    }
}

customElements.define(CalculatorView.is, CalculatorView);
