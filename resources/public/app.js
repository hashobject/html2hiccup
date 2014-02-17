goog.addDependency("base.js", ['goog'], []);
goog.addDependency("../cljs/core.js", ['cljs.core'], ['goog.string', 'goog.array', 'goog.object', 'goog.string.StringBuffer']);
goog.addDependency("../clojure/zip.js", ['clojure.zip'], ['cljs.core']);
goog.addDependency("../clojure/string.js", ['clojure.string'], ['cljs.core', 'goog.string', 'goog.string.StringBuffer']);
goog.addDependency("../hickory/utils.js", ['hickory.utils'], ['cljs.core', 'goog.string', 'clojure.string']);
goog.addDependency("../hickory/core.js", ['hickory.core'], ['cljs.core', 'goog.string', 'clojure.zip', 'hickory.utils']);
goog.addDependency("../html2hiccup/core.js", ['html2hiccup.core'], ['cljs.core', 'hickory.core']);