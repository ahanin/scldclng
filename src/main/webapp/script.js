// prototype.js/jquery-style locator
$ = function(id) {
    return document.getElementById(id)
}

// simple straight-forward uuid function
newUuid = function() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
        return v.toString(16);
    });
}

// IE<7 XMLHttpRequest fallback
if (!window.XMLHttpRequest) {
    window.XMLHttpRequest = function() {
        try {
            return new ActiveXObject('MSXML2.XMLHTTP.3.0');
        } catch (ex) {
            return null;
        }
    }
}

show = function(id) {
    var element = $(id);
    element.style.visibility = 'visible';
    element.style.display = 'block';
}