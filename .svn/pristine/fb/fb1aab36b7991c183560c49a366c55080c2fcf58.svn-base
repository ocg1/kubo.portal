var firmaRealizada1 = false;
console.log("firmaRealizada1"+firmaRealizada1 );
var canvas; var stage; var drawingCanvas; var oldPt; var oldMidPt; var title;
var color; var stroke; var record; var cacheCanvas; var replayMode = false;

function initF1(canvasID) {

  
  canvas = document.getElementById(canvasID);
  //check to see if we are running in a browser with touch support
  record = new Recorder();
  stage = new createjs.Stage(canvas);
  stage.autoClear = false;
  stage.enableDOMEvents(true);
  createjs.Touch.enable(stage);
  drawingCanvas = new createjs.Shape();
  
  if (!replayMode) {
	 
    stage.addEventListener('stagemousedown', handleMouseDown);
    stage.addEventListener('stagemouseup', handleMouseUp);
    stage.addEventListener('tick', tickHandler);
    title = new createjs.Text(
      'Firma aquí', '20px Open Sans', '#fff');
    title.x = canvas.width >> 1;
    title.y = canvas.height >> 1;
    title.textAlign = 'center';
    title.textBaseline = 'middle';
  
  }
  
  stage.addChild(title);
  stage.addChild(drawingCanvas);
  stage.update();
  // Settings
  stroke = 2;
  color = '#170DAB';

}
function handleMouseDown(event) {
	  
  if (!event.primary) { return; }
  if (stage.contains(title)) {
    stage.clear();
    stage.removeChild(title);
  }
  oldPt = new createjs.Point(event.stageX, event.stageY);
  oldMidPt = oldPt.clone();
  midPt = oldPt.clone();

  // If is not a replay
  if (!event.replay) {
    if (!replayMode) {
      stage.addEventListener('stagemousemove', handleMouseMove);
    }
    record.diffTime = Date.now() - Math.min(
      Date.now() - (record.diffTime || Date.now()), 2000);
    record.recordPoint(1, oldPt);
  }
}
function handleMouseMove(event) {

  if (!event.primary) { return; }
  var midPt = new createjs.Point(
      oldPt.x + event.stageX >> 1,
      oldPt.y + event.stageY >> 1
  );
  if (!event.replay || event.animated) { drawingCanvas.graphics.c(); }
  drawingCanvas.graphics.setStrokeStyle(stroke, 'round', 'round')
    .beginStroke(color).moveTo(midPt.x, midPt.y)
    .curveTo(oldPt.x, oldPt.y, oldMidPt.x, oldMidPt.y);

  oldPt.x = event.stageX;
  oldPt.y = event.stageY;
  oldMidPt.x = midPt.x;
  oldMidPt.y = midPt.y;

  if (!event.replay || event.animated) { stage.update(); }
  // If is not a replay
  if (!event.replay) {
    record.holdPoint(2, oldPt);
  }
}
function handleMouseUp(event) {
	
  if (!event.primary) { return; }
  if (!replayMode) {
    stage.removeEventListener('stagemousemove', handleMouseMove);
  }

  if (!event.replay || event.animated) { drawingCanvas.graphics.c(); }
  drawingCanvas.graphics.setStrokeStyle(stroke, 'round', 'round')
    .beginStroke(color).moveTo(midPt.x + 100, midPt.y + 100);

  if (!event.replay) {
    record.recordPoint(2, record.last);
    record.recordPoint(3, {x: event.stageX, y: event.stageY});
    record.play(replayMode);
  }
  firmaRealizada1 = true;
	canvasFirma ( firmaRealizada1, "medios");
	console.log("firmaRealizada1"+firmaRealizada1 );
}

var clear1 = function(event) {
  stage.clear();
  drawingCanvas.graphics.clear();
  record = new Recorder();
  firmaRealizada1 = false;
	
	console.log("firmaRealizada1"+firmaRealizada1 );
	canvasFirma ( firmaRealizada1, "medios");
};

var tickHandler = function(target, type, params) {
  record.tickHandler(target, type, params);
};

var Recorder = function() {
  this.buffer = [];
  this.diffTime = null;
  this.last = {};
};

Recorder.prototype.recordPoint = function(action, point) {
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

Recorder.prototype.holdPoint = function(action, point) {
  this.last.a = action;
  this.last.x = point.x;
  this.last.y = point.y;
  this.last.holding = true;
};

Recorder.prototype.tickHandler = function(target, type, params) {
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

Recorder.prototype.serialize = function() {
  return JSON.stringify(this.buffer, null, 0);
};

Recorder.prototype.deserialize = function(data) {
  this.buffer = JSON.parse(data);
  this.last = this.buffer[this.buffer.length - 1];
  this.diffTime = Date.now();
};

Recorder.prototype.play = function(animated, limit) {
  if (!replayMode) {
    stage.removeEventListener('stagemousedown', handleMouseDown);
    stage.removeEventListener('stagemouseup', handleMouseUp);
    stage.removeEventListener('stagemousemove', handleMouseMove);
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
        handleMouseDown(event);
      } else if (action.a == 2) {
        handleMouseMove(event);
      } else if (action.a == 3) {
        handleMouseUp(event);
      }
    }
    if (i >= limit) {
      clearInterval(this.interval);
      if (!animated) {
        record.diffTime = Date.now();
        stage.update();
        drawingCanvas.cache(bounds.x, bounds.y, bounds.w, bounds.h, 2);
        cacheCanvas = drawingCanvas.cacheCanvas;
        drawingCanvas.uncache();
        // console.log('uri', cacheCanvas.toDataURL());
      }
      if (!replayMode) {
        stage.addEventListener('stagemousedown', handleMouseDown);
        stage.addEventListener('stagemouseup', handleMouseUp);
      }
    }
  }).bind(this);

  clear1();
  record.buffer = this.buffer;
  if (!animated) {
    timeSum = 0;
    intervalFn();
  } else {
    this.interval = setInterval(intervalFn, (24 / 1000) | 0);
  }
};
