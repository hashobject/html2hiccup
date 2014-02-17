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
      vendor: {
        files: {
          'resources/public/vendor.js': ['resources/public/codemirror.js', 'resources/public/clojure.js', 'resources/public/htmlembedded.js'],
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
        src: 'resources/public/app.css',
        dest: 'resources/public/app.css'
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
          dest: 'index.html'
        },
        {
          src: 'resources/public/app.css',
          dest: 'app.css'
        },
        {
          src: 'resources/public/app.js',
          dest: 'app.js'
        },
        {
          src: 'resources/public/vendor.js',
          dest: 'vendor.js'
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

  grunt.registerTask('build', ['jade:compile', 'stylus:compile', 'concat:vendor']);
  grunt.registerTask('deploy', ['jade:compile', 'stylus:compile', 'cssmin:minify', 'concat:vendor', 's3:upload']);

};
