var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  // res.render('index', {title: 'Express'});
  res.redirect('/users/usuario');
});

router.get('/playback', function(req, res, next) {
  res.render('playback', {signature:
    req.query.signature || '[{"actionsets":[{"actions":[{"type":0,"x":116,"y":86},{"x":124,"y":83}],"interval":0},{"actions":[{"x":148,"y":66},{"x":167,"y":59},{"x":189,"y":47},{"x":209,"y":42}],"interval":101},{"actions":[{"x":274,"y":46},{"x":282,"y":52},{"x":290,"y":61}],"interval":201},{"actions":[{"x":293,"y":66},{"x":297,"y":74},{"x":301,"y":88},{"x":305,"y":106},{"x":305,"y":121},{"x":303,"y":135}],"interval":304},{"actions":[{"x":300,"y":143},{"x":296,"y":151},{"x":294,"y":154},{"x":293,"y":155},{"x":293,"y":156},{"x":292,"y":156}],"interval":405},{"actions":[{"x":292,"y":154},{"x":292,"y":149},{"x":297,"y":144},{"x":312,"y":142},{"x":330,"y":139},{"x":346,"y":139}],"interval":506},{"actions":[{"x":357,"y":140},{"x":364,"y":144},{"x":366,"y":147},{"x":369,"y":153},{"x":371,"y":163},{"x":374,"y":178}],"interval":607},{"actions":[{"x":374,"y":191},{"x":372,"y":205},{"x":370,"y":221},{"x":366,"y":233},{"x":362,"y":243},{"x":356,"y":252}],"interval":710},{"actions":[{"x":353,"y":257},{"x":350,"y":260},{"x":348,"y":261},{"x":347,"y":262},{"x":344,"y":263},{"x":340,"y":265}],"interval":811},{"actions":[{"x":333,"y":268},{"x":327,"y":270}],"interval":916},{"actions":[{"type":0,"x":201,"y":122},{"x":202,"y":129}],"interval":2038},{"actions":[{"x":206,"y":148},{"x":214,"y":174},{"x":216,"y":193},{"x":220,"y":210},{"x":222,"y":221},{"x":223,"y":223}],"interval":2139},{"actions":[{"x":224,"y":225},{"x":225,"y":226},{"x":230,"y":226},{"x":238,"y":227},{"x":250,"y":227},{"x":269,"y":227}],"interval":2239},{"actions":[{"x":289,"y":224},{"x":312,"y":219},{"x":328,"y":213},{"x":344,"y":206},{"x":354,"y":200},{"x":358,"y":198}],"interval":2339},{"actions":[{"x":360,"y":196},{"x":361,"y":196},{"x":361,"y":195}],"interval":2444}]}]'
  });
});

router.get('/experiment', function(req, res, next) {
  res.render('experiment');
});

module.exports = router;
