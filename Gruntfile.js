module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    aws: grunt.file.readJSON('grunt-aws.json'),

    stylus: {
      compile: {
        options: {
          paths: ['styl'],
          urlfunc: 'url',
          'include css': true,
          compress: false
        },
        files: {
          'resources/public/app.css': 'styl/app.styl'
        }
      }
    },

    jade: {
      compile: {
        options: {
          pretty: true,
          data: {
            debug: true
          }
        },
        files: {
          "resources/public/index.html": ["jade/index.jade"]
        }
      }
    },

    concat: {
      dist: {
        files: {
          'dist/js/vendor.js': ['js/jquery.js', 'js/fastclick.js', 'js/foundation.min.js', 'js/waypoints.min.js'],
        }
      }
    },

    uglify: {
      minify: {
        files: {
          'dist/js/app.min.js': ['dist/js/app.js'],
          'dist/js/vendor.min.js': ['dist/js/vendor.js']
        }
      }
    },

    watch: {
      src: {
        files: ['styl/*.styl', 'styl/foundation/*.styl', 'js/*.js', 'views/*.jade'],
        tasks: ['build']
      }
    },


    cssmin: {
      minify: {
        src: 'dist/css/app.css',
        dest: 'dist/css/app.min.css'
      }
    },

    s3: {
      key: '<%= aws.key %>',
      secret: '<%= aws.secret %>',
      bucket: '<%= aws.bucket %>',
      access: 'public-read',
      encodePaths: true,

      // Files to be uploaded.
      upload: [
        {
          src: 'resources/public/index.html',
          dest: 'html2hiccup/index.html'
        },
        {
          src: 'dist/robots.txt',
          dest: 'flatland/robots.txt'
        },
        {
          src: 'dist/css/*.css',
          dest: 'flatland/css'
        },
        {
          src: 'dist/css/images/*.png',
          dest: 'flatland/css/images'
        },
        {
          src: 'dist/css/images/*.jpg',
          dest: 'flatland/css/images'
        },
        {
          src: 'dist/js/*.js',
          dest: 'flatland/js/'
        }
      ]
    },

  });





  grunt.loadNpmTasks('grunt-contrib-stylus');
  grunt.loadNpmTasks('grunt-contrib-jade');
  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-s3');

  grunt.registerTask('build', ['jade:compile', 'stylus:compile']);

};
