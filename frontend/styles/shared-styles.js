// eagerly import theme styles so as we can override them
import '@vaadin/vaadin-lumo-styles/all-imports';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `

<custom-style>
<style>
    /**  Fix iOS height problem **/
    body {
        min-height: 100vh;
        min-height: -webkit-fill-available;
        height: 100%;
    }
    html {
        height: -webkit-fill-available;
    }
</style>
</custom-style>


`;

document.head.appendChild($_documentContainer.content);
