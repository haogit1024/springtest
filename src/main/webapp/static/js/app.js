window.onload = function () {
    var parent = document.getElementById("mydiv");
    var pElement = document.createElement("p");
    var textNode = document.createTextNode("this is create text node");
    parent.appendChild(pElement);
    pElement.appendChild(textNode);
};
