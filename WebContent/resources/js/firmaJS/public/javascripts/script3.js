var firmaRealizada3 = false;
console.log("firmaRealizada3"+firmaRealizada3 );
var canvas3; var stage3; var drawingCanvas3; var oldPt3; var oldMidPt3; var title3;
var color3; var stroke3; var record3; var cacheCanvas3; var replayMode3 = false;

function init3(canvasID) {
	console.log( "init3("+canvasID+")" );
  canvas3 = document.getElementById(canvasID);
  //check to see if we are running in a browser with touch support
  record3 = new Recorder3();
stage3 = new createjs.Stage(canvas3);
stage3.autoClear = false;
stage3.enableDOMEvents(true);
  createjs.Touch.enable(stage3);
  drawingCanvas3 = new createjs.Shape();
  if (!replayMode3) {
  stage3.addEventListener('stagemousedown', handleMouseDown3);
  stage3.addEventListener('stagemouseup', handleMouseUp3);
  stage3.addEventListener('tick', tickHandler3);
    title3 = new createjs.Text(
      'Firma aquí', '20px Open Sans', '#fff');
    title3.x = canvas3.width >> 1;
    title3.y = canvas3.height >> 1;
    title3.textAlign = 'center';
    title3.textBaseline = 'middle';
 }
  
stage3.addChild(title3);
stage3.addChild(drawingCanvas3);
stage3.update();
  // Settings
  stroke3 = 2;
  color3 = '#170DAB';

}
function handleMouseDown3(event) {
	  
  if (!event.primary) { return; }
  if (stage3.contains(title3)) {
  stage3.clear();
  stage3.removeChild(title3);
  }
  oldPt3 = new createjs.Point(event.stageX, event.stageY);
  oldMidPt3 = oldPt3.clone();
  midPt = oldPt3.clone();

  // If is not a replay
  if (!event.replay) {
    if (!replayMode3) {
    stage3.addEventListener('stagemousemove', handleMouseMove3);
    }
    record3.diffTime = Date.now() - Math.min(
      Date.now() - (record3.diffTime || Date.now()), 3000);
    record3.recordPoint(1, oldPt3);
  }
}
function handleMouseMove3(event) {

  if (!event.primary) { return; }
  var midPt = new createjs.Point(
      oldPt3.x + event.stageX >> 1,
      oldPt3.y + event.stageY >> 1
  );
  if (!event.replay || event.animated) { drawingCanvas3.graphics.c(); }
  drawingCanvas3.graphics.setStrokeStyle(stroke3, 'round', 'round')
    .beginStroke(color3).moveTo(midPt.x, midPt.y)
    .curveTo(oldPt3.x, oldPt3.y, oldMidPt3.x, oldMidPt3.y);

  oldPt3.x = event.stageX;
  oldPt3.y = event.stageY;
  oldMidPt3.x = midPt.x;
  oldMidPt3.y = midPt.y;

  if (!event.replay || event.animated) {stage3.update(); }
  // If is not a replay
  if (!event.replay) {
    record3.holdPoint(2, oldPt3);
  }
}
function handleMouseUp3(event) {
	
  if (!event.primary) { return; }
  if (!replayMode3) {
  stage3.removeEventListener('stagemousemove', handleMouseMove3);
  }

  if (!event.replay || event.animated) { drawingCanvas3.graphics.c(); }
  drawingCanvas3.graphics.setStrokeStyle(stroke3, 'round', 'round')
    .beginStroke(color3).moveTo(midPt.x + 100, midPt.y + 100);

  if (!event.replay) {
    record3.recordPoint(2, record3.last);
    record3.recordPoint(3, {x: event.stageX, y: event.stageY});
    record3.play(replayMode3);
  }
  firmaRealizada3 = true;
	console.log("firmaRealizada3"+firmaRealizada3 );
	canvasFirma (firmaRealizada3, "garantia");
}

var clear3= function(event) {
stage3.clear();
  drawingCanvas3.graphics.clear();
  record3 = new Recorder3();
  firmaRealizada3 = false;
	console.log("firmaRealizada3"+firmaRealizada3 )
	canvasFirma (firmaRealizada3, "garantia");
};

var tickHandler3 = function(target, type, params) {
  record3.tickHandler3(target, type, params);
};

var Recorder3 = function() {
  this.buffer = [];
  this.diffTime = null;
  this.last = {};
};

Recorder3.prototype.recordPoint = function(action, point) {
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

Recorder3.prototype.holdPoint = function(action, point) {
  this.last.a = action;
  this.last.x = point.x;
  this.last.y = point.y;
  this.last.holding = true;
};

Recorder3.prototype.tickHandler3 = function(target, type, params) {
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

Recorder3.prototype.serialize = function() {
  return JSON.stringify(this.buffer, null, 0);
};

Recorder3.prototype.deserialize = function(data) {
  this.buffer = JSON.parse(data);
  this.last = this.buffer[this.buffer.length - 1];
  this.diffTime = Date.now();
};

Recorder3.prototype.play = function(animated, limit) {
  if (!replayMode3) {
  stage3.removeEventListener('stagemousedown', handleMouseDown3);
  stage3.removeEventListener('stagemouseup', handleMouseUp3);
  stage3.removeEventListener('stagemousemove', handleMouseMove3);
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
        handleMouseDown3(event);
      } else if (action.a == 2) {
        handleMouseMove3(event);
      } else if (action.a == 3) {
        handleMouseUp3(event);
      }
    }
    if (i >= limit) {
      clearInterval(this.interval);
      if (!animated) {
        record3.diffTime = Date.now();
      stage3.update();
        drawingCanvas3.cache(bounds.x, bounds.y, bounds.w, bounds.h, 2);
        cacheCanvas3 = drawingCanvas3.cacheCanvas;
        drawingCanvas3.uncache();
        // console.log('uri', cacheCanvas2.toDataURL());
      }
      if (!replayMode3) {
      stage3.addEventListener('stagemousedown', handleMouseDown3);
      stage3.addEventListener('stagemouseup', handleMouseUp3);
      }
    }
  }).bind(this);

  clear3();
  record3.buffer = this.buffer;
  if (!animated) {
    timeSum = 0;
    intervalFn();
  } else {
    this.interval = setInterval(intervalFn, (24 / 1000) | 0);
  }
};
