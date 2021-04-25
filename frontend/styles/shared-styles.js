// eagerly import theme styles so as we can override them
import '@vaadin/vaadin-lumo-styles/all-imports';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `
<dom-module id="ark-delegate-form-item" theme-for="vaadin-form-item">
    <template>
        <style>
        /*[part="label"] {*/
        /*    color: var(--lumo-secondary-text-color);*/
        /*    font-family: var(--lumo-font-family);*/
        /*    font-size: var(--lumo-font-size-s);*/
        /*    font-weight: bold;*/
        /*    margin-top: var(--lumo-space-m);*/
        /*    margin-left: calc(var(--lumo-border-radius) / 4);*/
        /*    margin-bottom: var(--lumo-space-xs);*/
        /*    transition: color 0.4s;*/
        /*    line-height: 1.333;*/
        /*}*/
        
        /*#content ::slotted(#content-s) {*/
        /*    font-size: var(--lumo-font-size-m);*/
        /*}*/
        </style>
    </template>
</dom-module>

<custom-style>
<style include='lumo-badge'>
</style>
</custom-style>


`;

document.head.appendChild($_documentContainer.content);
