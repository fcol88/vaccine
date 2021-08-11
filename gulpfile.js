
const gulp = require('gulp');
const sass = require('gulp-sass')(require('sass'));
const del = require('del');
const purgecss = require('gulp-purgecss');

gulp.task('styles', () => {
	return bulma = gulp.src('src/main/resources/static/scss/style.scss').
		pipe(sass().on('error', sass.logError))
		.pipe(gulp.dest('src/main/resources/static/css/raw'));
});

gulp.task('clean', () => {
	return del([
		'src/main/resources/static/css/style.css',
		'src/main/resources/static/css/raw/style.css'
	]);
});

gulp.task('purgecss', () => {
	return gulp.src('src/main/resources/static/css/raw/style.css')
		.pipe(purgecss({
			content: ['src/main/resources/templates/*.html', 'src/main/resources/templates/**/*.html']
		}))
		.pipe(gulp.dest('src/main/resources/static/css'))
});

gulp.task('default', gulp.series(['clean', 'styles', 'purgecss']));