!function () {
    "use strict";
    var o = [], n = 0, e = 0, t = {
            window: "object" == typeof window,
            module: "object" == typeof module && "object" == typeof module.exports,
            amd: "function" == typeof define && define.amd,
            global: "object" == typeof global
        }, r = {defineProp: "undefined" != typeof Object.defineProperty},
        a = {valuesCount: 1e4, randomInterval: 30, removeOptions: !0}, d = {};
    t.window && window.fastrandomOptions ? d = window.fastrandomOptions : t.global && global.fastrandomOptions ? d = global.fastrandomOptions : "object" == typeof fastrandomOptions && (d = fastrandomOptions);
    for (var i in a) a.hasOwnProperty(i) && !d.hasOwnProperty(i) && (d[i] = a[i]);
    d.removeOptions && (t.window && window.fastrandomOptions ? delete window.fastrandomOptions : t.global && global.fastrandomOptions && delete global.fastrandomOptions);
    var f = function (e) {
        return e ? o[e] : (n == d.valuesCount && (n = 0), o[n++])
    };
    f.randomChanger = function () {
        e == d.valuesCount && (e = 0), o[e++] = Math.random()
    };
    for (var s = 0; s < d.valuesCount; s++) f.randomChanger();
    d.randomInterval && setInterval(f.randomChanger, d.randomInterval), f.valueOf = f, f.options = d, r.defineProp && (Object.defineProperty(f, "random", {
        get: function () {
            return f()
        }
    }), Object.defineProperty(f, "randomArray", {
        get: function () {
            return o
        }
    }), Object.defineProperty(f, "currentRandomIndex", {
        get: function () {
            return n
        }
    })), t.module ? module.exports = f : t.amd && define("fastrandom", [], function () {
        return f
    }), t.window && (window.random = f)
}();

/**
 * Init
 */
var canvas = document.getElementsByClassName('canvas')[0];

window.onresize = function () {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
};

window.onresize();

var ctx = canvas.getContext('2d');

ctx.font = 'bold 200px "Arial"';
ctx.textBaseline = 'center';
ctx.fillStyle = '#000';

var _particles = [];
var particlesLength = 0;

var currentText = "Blue";

if (!window.requestAnimationFrame) {
    window.requestAnimationFrame = window.mozRequestAnimationFrame
        || window.webkitRequestAnimationFrame
        || window.msRequestAnimationFrame;
}

/**
 * Create one particle
 * @param x
 * @param y
 */
var createParticle = function createParticle(x, y) {
    _particles.push(new Particle(x, y));
};

/**
 * Check if pixel has alpha
 * @param pixels
 * @param i
 * @returns {boolean}
 */
var checkAlpha = function checkAlpha(pixels, i) {
    return pixels[i * 4 + 3] > 0;
};

/**
 * Create _particles
 */
var createParticles = function createParticles() {
    var textSize = ctx.measureText(currentText);
    ctx.fillText(
        currentText,
        Math.round((canvas.width / 2) - (textSize.width / 2)),
        Math.round(canvas.height / 2)
    );

    var imageData = ctx.getImageData(1, 1, canvas.width, canvas.height);
    var pixels = imageData.data;
    var dataLength = imageData.width * imageData.height;

    //Loop through all pixels
    for (var i = 0; i < dataLength; i++) {
        var currentRow = Math.floor(i / imageData.width);
        var currentColumn = i - Math.floor(i / imageData.height);

        if (currentRow % 2 || currentColumn % 2) {
            continue;
        }

        //If alpha channel is greater than 0
        if (checkAlpha(pixels, i)) {
            var cy = ~~(i / imageData.width);
            var cx = ~~(i - (cy * imageData.width));

            createParticle(cx, cy);
        }
    }

    particlesLength = _particles.length;
};

/**
 * new Point(x, y)
 * @param x pointer
 * @param y pointer
 * @constructor
 */
var Point = function Point(x, y) {
    this.set(x, y);
};

Point.prototype = {
    set: function (x, y) {
        x = x || 0;
        y = y || x || 0;

        /**
         * x start pointer
         * @type {*|number}
         * @private
         */
        this._sX = x;

        /**
         * y start pointer
         * @type {*|number}
         * @private
         */
        this._sY = y;

        /**
         * Call reset
         */
        this.reset();
    },

    /**
     * add one point to another
     * @param point
     */
    add: function (point) {
        this.x += point.x;
        this.y += point.y;
    },

    /**
     * multiply two points
     * @param point
     */
    multiply: function (point) {
        this.x *= point.x;
        this.y *= point.y;
    },

    /**
     * Reset point
     */
    reset: function () {
        /**
         * x pointer
         * @type {*|number}
         */
        this.x = this._sX;

        /**
         * y pointer
         * @type {*|number}
         */
        this.y = this._sY;

        return this;
    },
};

var FRICT = new Point(0.98);
/**
 * Particle constructor
 * @param x
 * @param y
 * @constructor
 */
var Particle = function Particle(x, y) {
    this.startPos = new Point(x, y);

    /**
     * Movement variables
     */
    this.v = new Point();
    this.a = new Point();

    /**
     * First init reset
     */
    this.reset();
};

Particle.prototype = {
    /**
     * Reset particle
     */
    reset: function () {
        this.x = this.startPos.x;
        this.y = this.startPos.y;

        this.life = Math.round(random() * 300);
        this.isActive = true;

        /**
         * Movement variables
         */
        this.v.reset();
        this.a.reset();
    },
    /**
     * Particle tick
     */
    tick: function () {
        if (!this.isActive) return;

        this.physics();
        this.checkLife();

        this.draw();

        return this.isActive;
    },
    /**
     * Calculate life
     */
    checkLife: function () {
        this.life -= 1;

        this.isActive = !(this.life < 1);
    },

    /**
     * Draw particle
     */
    draw: function () {
        ctx.fillRect(this.x, this.y, 1, 1);
    },

    /**
     * Calculate particle movement
     */
    physics: function () {
        this.a.x = (random() - 0.5) * 0.8;
        this.a.y = (random() - 0.5) * 0.8;

        this.v.add(this.a);
        this.v.multiply(FRICT);

        this.x += this.v.x;
        this.y += this.v.y;

        this.x = Math.round(this.x * 10) / 10;
        this.y = Math.round(this.y * 10) / 10;
    }
};

/**
 * Start the party
 */
createParticles();

/**
 * Clear canvas
 */
function clearCanvas() {
    ctx.fillStyle = 'rgba(0,0,0,0.2)';

    ctx.fillRect(0, 0, canvas.width, canvas.height);
}

(function clearLoop() {
    /**
     * Do clearing
     */
    clearCanvas();

    /**
     * next loop
     */
    requestAnimationFrame(clearLoop);
})();

/**
 * Main animation loop
 */
(function animLoop() {
    ctx.fillStyle = '#2c87c4';
    var isAlive = false;

    /**
     * Loop _particles
     */
    for (var i = 0; i < particlesLength; i++) {
        /**
         * If particle is active
         */
        if (_particles[i].tick()) isAlive = true;
    }

    if (!isAlive) {
        resetParticles();

        setTimeout(function () {
            requestAnimationFrame(animLoop);
        }, 500);

        return;
    }

    /**
     * next loop
     */
    requestAnimationFrame(animLoop);
})();

function resetParticles() {
    for (var i = 0; i < particlesLength; i++) {
        _particles[i].reset();
    }
}