document.addEventListener("DOMContentLoaded", function (event) {

    const showNavbar = (toggleId, navId, bodyId) => {
        const toggle = document.getElementById(toggleId),
            nav = document.getElementById(navId),
            bodypd = document.getElementById(bodyId);

        // Validate that all variables exist
        if (toggle && nav && bodypd) {
            toggle.addEventListener('click', () => {
                const isOpenning = nav.classList.contains("show");
                if (isOpenning) {
                    document.querySelector(".expand").style.display = 'none';
                    document.querySelector(".collapse").style.display = 'flex';
                } else {
                    document.querySelector(".expand").style.display = 'flex';
                    document.querySelector(".collapse").style.display = 'none';
                }
                // show navbar
                nav.classList.toggle('show')
                // change icon
                toggle.classList.toggle('bx-x')
                // add padding to body
                bodypd.classList.toggle('body-pd')
            })
        }
    }

    showNavbar('header-toggle', 'nav-bar', 'body-pd')

    /*===== LINK ACTIVE =====*/
    const linkColor = document.querySelectorAll('.nav_link')

    function colorLink() {
        if (!this.classList.contains("toggle-sidebar") && linkColor) {
            linkColor.forEach(l => l.classList.remove('active'))
            this.classList.add('active')
        }
    }
    linkColor.forEach(l => l.addEventListener('click', colorLink))

    // Your code to run since DOM is loaded and ready
});