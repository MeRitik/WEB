console.log('script.js loaded');

let currentTheme = getTheme();

// init
changeTheme(currentTheme);
document.getElementById('theme_change_button').querySelector('span').textContent = (currentTheme == "dark" ? "Light" : "Dark");

// TODO:
function changeTheme() {
    // get the html tag
    document.querySelector('html').classList.add(currentTheme);

    // set the listener to change theme button
    const changeThemeBtn = document.getElementById('theme_change_button');
    changeThemeBtn.addEventListener("click", (event) => {
        const oldTheme = currentTheme;
        if(currentTheme === "dark") {
            currentTheme = "light";
        }else {
            currentTheme = "dark";
        }
        setTheme(currentTheme);
        document.querySelector('html').classList.remove(oldTheme);
        document.querySelector('html').classList.add(currentTheme);
        
        changeThemeBtn.querySelector('span').textContent = (currentTheme == "dark" ? "Light" : "Dark");
    });
}

// set theme to localStorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

// get theme from localStorage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}