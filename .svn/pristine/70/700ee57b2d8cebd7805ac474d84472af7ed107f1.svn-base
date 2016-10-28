var firmaRealizada4 = false;
console.log("firmaRealizada4"+firmaRealizada4 );
var canvas4; var stage4; var drawingCanvas4; var oldPt4; var oldMidPt4; var title4;
var color4; var stroke4; var record4; var cacheCanvas4; var replayMode4 = false;

function init4(canvasID) {
	console.log( "init4("+canvasID+")" );
  canvas4 = document.getElementById(canvasID);
  //check to see if we are running in a browser with touch support
  record4 = new Recorder4();
stage4 = new createjs.Stage(canvas4);
stage4.autoClear = false;
stage4.enableDOMEvents(true);
  createjs.Touch.enable(stage4);
  drawingCanvas4 = new createjs.Shape();
  if (!replayMode4) {
  stage4.addEventListener('stagemousedown', handleMouseDown4);
  stage4.addEventListener('stagemouseup', handleMouseUp4);
  stage4.addEventListener('tick', tickHandler4);
    title4 = new createjs.Text(
      'Firma aquí', '20px Open Sans', '#fff');
    title4.x = canvas4.width >> 1;
    title4.y = canvas4.height >> 1;
    title4.textAlign = 'center';
    title4.textBaseline = 'middle';
 }
  
stage4.addChild(title4);
stage4.addChild(drawingCanvas4);
stage4.update();
  // Settings
  stroke4 = 2;
  color4 = '#170DAB';

}
function handleMouseDown4(event) {
	  
  if (!event.primary) { return; }
  if (stage4.contains(title4)) {
  stage4.clear();
  stage4.removeChild(title4);
  }
  oldPt4 = new createjs.Point(event.stageX, event.stageY);
  oldMidPt4 = oldPt4.clone();
  midPt = oldPt4.clone();

  // If is not a replay
  if (!event.replay) {
    if (!replayMode4) {
    stage4.addEventListener('stagemousemove', handleMouseMove4);
    }
    record4.diffTime = Date.now() - Math.min(
      Date.now() - (record4.diffTime || Date.now()), 3000);
    record4.recordPoint(1, oldPt4);
  }
}
function handleMouseMove4(event) {

  if (!event.primary) { return; }
  var midPt = new createjs.Point(
      oldPt4.x + event.stageX >> 1,
      oldPt4.y + event.stageY >> 1
  );
  if (!event.replay || event.animated) { drawingCanvas4.graphics.c(); }
  drawingCanvas4.graphics.setStrokeStyle(stroke4, 'round', 'round')
    .beginStroke(color4).moveTo(midPt.x, midPt.y)
    .curveTo(oldPt4.x, oldPt4.y, oldMidPt4.x, oldMidPt4.y);

  oldPt4.x = event.stageX;
  oldPt4.y = event.stageY;
  oldMidPt4.x = midPt.x;
  oldMidPt4.y = midPt.y;

  if (!event.replay || event.animated) {stage4.update(); }
  // If is not a replay
  if (!event.replay) {
    record4.holdPoint(2, oldPt4);
  }
}
function handleMouseUp4(event) {
	
  if (!event.primary) { return; }
  if (!replayMode4) {
  stage4.removeEventListener('stagemousemove', handleMouseMove4);
  }

  if (!event.replay || event.animated) { drawingCanvas4.graphics.c(); }
  drawingCanvas4.graphics.setStrokeStyle(stroke4, 'round', 'round')
    .beginStroke(color4).moveTo(midPt.x + 100, midPt.y + 100);

  if (!event.replay) {
    record4.recordPoint(2, record4.last);
    record4.recordPoint(3, {x: event.stageX, y: event.stageY});
    record4.play(replayMode4);
  }
  firmaRealizada4 = true;
	console.log("firmaRealizada4"+firmaRealizada4 );
	canvasFirma (firmaRealizada4, "acreditado");
}

var clear4= function(event) {
stage4.clear();
  drawingCanvas4.graphics.clear();
  record4 = new Recorder4();
  firmaRealizada4 = false;
	console.log("firmaRealizada4"+firmaRealizada4 )
	canvasFirma (firmaRealizada4, "acreditado");
};

var tickHandler4 = function(target, type, params) {
  record4.tickHandler4(target, type, params);
};

var Recorder4 = function() {
  this.buffer = [];
  this.diffTime = null;
  this.last = {};
};

Recorder4.prototype.recordPoint = function(action, point) {
  if (!this.diffTime) { this.diffTime = Date.now(); }
  var time = Date.now() - this.diffTime;
  var rec = {a: action, x: point.x, y: point.y, t: time};
  this.diffTime = Date.now();
  this.buffer.push(rec);
  if (!action) {
    throw new Error();
  }
  this.last = {a: action, x: point.x, y: point.y, t: time};
};

Recorder4.prototype.holdPoint = function(action, point) {
  this.last.a = action;
  this.last.x = point.x;
  this.last.y = point.y;
  this.last.holding = true;
};

Recorder4.prototype.tickHandler4 = function(target, type, params) {
  if (!this.diffTime) { return; }
  var last = this.buffer[this.buffer.length - 1];
  if (
    last.a == this.last.a &&
    (last.x >> 2) == (this.last.x >> 2) &&
    (last.y >> 2) == (this.last.y >> 2)
  ) {
    return;
  }
  if (this.last.holding) {
    this.recordPoint(this.last.a, this.last);
  }
};

Recorder4.prototype.serialize = function() {
  return JSON.stringify(this.buffer, null, 0);
};

Recorder4.prototype.deserialize = function(data) {
  this.buffer = JSON.parse(data);
  this.last = this.buffer[this.buffer.length - 1];
  this.diffTime = Date.now();
};

Recorder4.prototype.play = function(animated, limit) {
  if (!replayMode4) {
  stage4.removeEventListener('stagemousedown', handleMouseDown4);
  stage4.removeEventListener('stagemouseup', handleMouseUp4);
  stage4.removeEventListener('stagemousemove', handleMouseMove4);
  }
  if (this.interval) { return; }

  var i = 0; var timeSum = Date.now();
  var bounds = {x: Infinity, y: Infinity, xf: -Infinity, yf: -Infinity};
  if (isNaN(limit)) { limit = this.buffer.length; }
  var intervalFn = (function() {
    for (; i < limit && timeSum < Date.now(); i++) {
      var action = this.buffer[i];
      timeSum += action.t;
      var event = {
        primary: true, replay: true, animated: !!animated,
        stageX: action.x, stageY: action.y
      };
      bounds.x = Math.min(bounds.x, action.x - 5);
      bounds.y = Math.min(bounds.y, action.y - 5);
      bounds.xf = Math.max(bounds.xf, action.x + 5);
      bounds.yf = Math.max(bounds.yf, action.y + 5);
      bounds.w = bounds.xf - bounds.x;
      bounds.h = bounds.yf - bounds.y;
      if (action.a == 1) {
        handleMouseDown4(event);
      } else if (action.a == 2) {
        handleMouseMove4(event);
      } else if (action.a == 3) {
        handleMouseUp4(event);
      }
    }
    if (i >= limit) {
      clearInterval(this.interval);
      if (!animated) {
        record4.diffTime = Date.now();
      stage4.update();
        drawingCanvas4.cache(bounds.x, bounds.y, bounds.w, bounds.h, 2);
        cacheCanvas4 = drawingCanvas4.cacheCanvas;
        drawingCanvas4.uncache();
        // console.log('uri', cacheCanvas2.toDataURL());
      }
      if (!replayMode4) {
      stage4.addEventListener('stagemousedown', handleMouseDown4);
      stage4.addEventListener('stagemouseup', handleMouseUp4);
      }
    }
  }).bind(this);

  clear4();
  record4.buffer = this.buffer;
  if (!animated) {
    timeSum = 0;
    intervalFn();
  } else {
    this.interval = setInterval(intervalFn, (24 / 1000) | 0);
  }
};
