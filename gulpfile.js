
const gulp = require('gulp');
const sass = require('gulp-sass');
const del = require('del');

gulp.task('styles', () => {
	return bulma = gulp.src('src/main/resources/static/scss/style.scss').
		pipe(sass().on('error', sass.logError))
		.pipe(gulp.dest('src/main/resources/static/css/'));
});

gulp.task('clean', () => {
	return del([
		'src/main/resources/static/css/style.css',
	]);
})

gulp.task('default', gulp.series(['clean', 'styles']));