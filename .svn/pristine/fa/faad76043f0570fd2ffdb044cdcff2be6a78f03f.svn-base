var firmaRealizada2 = false;
console.log("firmaRealizada2"+firmaRealizada2 );
var canvas2; var stage2; var drawingCanvas2; var oldPt2; var oldMidPt2; var title2;
var color2; var stroke2; var record2; var cacheCanvas2; var replayMode2 = false;

function init2(canvasID) {
	console.log( "init2("+canvasID+")" );
  canvas2 = document.getElementById(canvasID);
  //check to see if we are running in a browser with touch support
  record2 = new Recorder2();
stage2 = new createjs.Stage(canvas2);
stage2.autoClear = false;
stage2.enableDOMEvents(true);
  createjs.Touch.enable(stage2);
  drawingCanvas2 = new createjs.Shape();
  if (!replayMode2) {
  stage2.addEventListener('stagemousedown', handleMouseDown2);
  stage2.addEventListener('stagemouseup', handleMouseUp2);
  stage2.addEventListener('tick', tickHandler2);
    title2 = new createjs.Text(
      'Firma aquí', '20px Open Sans', '#fff');
    title2.x = canvas2.width >> 1;
    title2.y = canvas2.height >> 1;
    title2.textAlign = 'center';
    title2.textBaseline = 'middle';
  }
stage2.addChild(title2);
stage2.addChild(drawingCanvas2);
stage2.update();
  // Settings
  stroke2 = 2;
  color2 = '#170DAB';

}
function handleMouseDown2(event) {
	  
  if (!event.primary) { return; }
  if (stage2.contains(title2)) {
  stage2.clear();
  stage2.removeChild(title2);
  }
  oldPt2 = new createjs.Point(event.stageX, event.stageY);
  oldMidPt2 = oldPt2.clone();
  midPt = oldPt2.clone();

  // If is not a replay
  if (!event.replay) {
    if (!replayMode2) {
    stage2.addEventListener('stagemousemove', handleMouseMove2);
    }
    record2.diffTime = Date.now() - Math.min(
      Date.now() - (record2.diffTime || Date.now()), 2000);
    record2.recordPoint(1, oldPt2);
  }
}
function handleMouseMove2(event) {

  if (!event.primary) { return; }
  var midPt = new createjs.Point(
      oldPt2.x + event.stageX >> 1,
      oldPt2.y + event.stageY >> 1
  );
  if (!event.replay || event.animated) { drawingCanvas2.graphics.c(); }
  drawingCanvas2.graphics.setStrokeStyle(stroke2, 'round', 'round')
    .beginStroke(color2).moveTo(midPt.x, midPt.y)
    .curveTo(oldPt2.x, oldPt2.y, oldMidPt2.x, oldMidPt2.y);

  oldPt2.x = event.stageX;
  oldPt2.y = event.stageY;
  oldMidPt2.x = midPt.x;
  oldMidPt2.y = midPt.y;

  if (!event.replay || event.animated) {stage2.update(); }
  // If is not a replay
  if (!event.replay) {
    record2.holdPoint(2, oldPt2);
  }
}
function handleMouseUp2(event) {
	
  if (!event.primary) { return; }
  if (!replayMode2) {
  stage2.removeEventListener('stagemousemove', handleMouseMove2);
  }

  if (!event.replay || event.animated) { drawingCanvas2.graphics.c(); }
  drawingCanvas2.graphics.setStrokeStyle(stroke2, 'round', 'round')
    .beginStroke(color2).moveTo(midPt.x + 100, midPt.y + 100);

  if (!event.replay) {
    record2.recordPoint(2, record2.last);
    record2.recordPoint(3, {x: event.stageX, y: event.stageY});
    record2.play(replayMode2);
  }
  firmaRealizada2 = true;
	console.log("firmaRealizada2"+firmaRealizada2 );
	canvasFirma (firmaRealizada2, "captacion");
}

var clear2= function(event) {
stage2.clear();
  drawingCanvas2.graphics.clear();
  record2 = new Recorder2();
  firmaRealizada2 = false;
	console.log("firmaRealizada2"+firmaRealizada2 );
	canvasFirma (firmaRealizada2, "captacion");
};

var tickHandler2 = function(target, type, params) {
  record2.tickHandler2(target, type, params);
};

var Recorder2 = function() {
  this.buffer = [];
  this.diffTime = null;
  this.last = {};
};

Recorder2.prototype.recordPoint = function(action, point) {
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

Recorder2.prototype.holdPoint = function(action, point) {
  this.last.a = action;
  this.last.x = point.x;
  this.last.y = point.y;
  this.last.holding = true;
};

Recorder2.prototype.tickHandler2 = function(target, type, params) {
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

Recorder2.prototype.serialize = function() {
  return JSON.stringify(this.buffer, null, 0);
};

Recorder2.prototype.deserialize = function(data) {
  this.buffer = JSON.parse(data);
  this.last = this.buffer[this.buffer.length - 1];
  this.diffTime = Date.now();
};

Recorder2.prototype.play = function(animated, limit) {
  if (!replayMode2) {
  stage2.removeEventListener('stagemousedown', handleMouseDown2);
  stage2.removeEventListener('stagemouseup', handleMouseUp2);
  stage2.removeEventListener('stagemousemove', handleMouseMove2);
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
        handleMouseDown2(event);
      } else if (action.a == 2) {
        handleMouseMove2(event);
      } else if (action.a == 3) {
        handleMouseUp2(event);
      }
    }
    if (i >= limit) {
      clearInterval(this.interval);
      if (!animated) {
        record2.diffTime = Date.now();
      stage2.update();
        drawingCanvas2.cache(bounds.x, bounds.y, bounds.w, bounds.h, 2);
        cacheCanvas2 = drawingCanvas2.cacheCanvas;
        drawingCanvas2.uncache();
        // console.log('uri', cacheCanvas2.toDataURL());
      }
      if (!replayMode2) {
      stage2.addEventListener('stagemousedown', handleMouseDown2);
      stage2.addEventListener('stagemouseup', handleMouseUp2);
      }
    }
  }).bind(this);

  clear2();
  record2.buffer = this.buffer;
  if (!animated) {
    timeSum = 0;
    intervalFn();
  } else {
    this.interval = setInterval(intervalFn, (24 / 1000) | 0);
  }
};
