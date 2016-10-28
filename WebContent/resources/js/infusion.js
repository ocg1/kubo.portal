(function() {
    var trackingCode = "09947f74b936893f4fa6f02a613fa3cf";
    var baseURL = "https://su293.infusionsoft.com";
    var image = new Image(1, 1);
    image.onLoad = function() {};
    var currentTime = new Date().getTime();
    image.src = baseURL + '/app/webTracking/track/' + currentTime + buildRequestParams();
    function buildRequestParams() {
        var resolution = screen.width + "x" + screen.height;
        var java = navigator.javaEnabled() ? '&javaEnabled=true' : '';
        var pluginList = "";
        var pluginString = "";
        if (window.ActiveXObject) {
            pluginString = detectIEPlugins();
        } else {
            for (var i = 0; i < navigator.plugins.length; i++) {
                pluginList = pluginList + navigator.plugins[i].name + ',';
            }
            pluginString = pluginCompatibility(pluginList);
        }
        if (pluginString == "") {
            pluginString = "No Plugins";
        } else {
            pluginString = pluginString.substring(0, pluginString.lastIndexOf(','));
        }
        var referrer = encodeURIComponent(document.referrer);
        var queryString = '?trackingCode=' + trackingCode + '&screenResolution=' + resolution + '&plugins=' + pluginString + java;
        if (referrer == "" || referrer == null) {
            queryString = queryString + "&location=" + window.location;
        } else {
            queryString = queryString + "&referrer=" + referrer;
        }
        return queryString;
    }
    function pluginCompatibility(plugins) {
        var pluginString = "";
        if (plugins.indexOf("Adobe Reader") > 0) {
            pluginString += "Adobe Reader,";
        }
        if (plugins.indexOf("Flash") > 0) {
            pluginString += "Flash,";
        }
        if (plugins.indexOf("Shockwave") > 0 || plugins.indexOf("director") > 0) {
            pluginString += "Shockwave,";
        }
        if (plugins.indexOf("QuickTime") > 0) {
            pluginString += "Quick Time,";
        }
        if (plugins.indexOf("RealPlayer") > 0 || plugins.indexOf("Real Player") > 0) {
            pluginString += "RealPlayer,";
        }
        if (plugins.indexOf("Windows Media Player") > 0) {
            pluginString += "Windows Media Player,";
        }
        if (plugins.indexOf("Silverlight") > 0) {
            pluginString += "Silverlight,";
        }
        return pluginString;
    }
    function detectIEPlugins() {
        var activeXNames = {'AcroPDF.PDF':'Adobe Reader',
            'ShockwaveFlash.ShockwaveFlash':'Flash',
            'QuickTime.QuickTime':'Quick Time',
            'SWCtl':'Shockwave',
            'WMPLayer.OCX':'Windows Media Player',
            'AgControl.AgControl':'Silverlight'};
        var plugins = "";
        var plugin = null;
        for (var activeKey in activeXNames) {
            try {
                plugin = null;
                plugin = new ActiveXObject(activeKey);
            } catch (e) {
                // do nothing, the plugin is not installed
            }
            plugins += activeXNames[activeKey] + ",";
        }
        var realPlayerNames = ['rmockx.RealPlayer G2 Control',
            'rmocx.RealPlayer G2 Control.1',
            'RealPlayer.RealPlayer(tm) ActiveX Control (32-bit)',
            'RealVideo.RealVideo(tm) ActiveX Control (32-bit)',
            'RealPlayer'];
        for (var i = 0; i < realPlayerNames.length; i++) {
            try {
                plugin = new ActiveXObject(realPlayerNames[i]);
            } catch (e) {
                continue;
            }
            if (plugin) {
                break;
            }
        }
        if (plugin) {
            plugins += "RealPlayer,";
        }
        return plugins;
    }
})();