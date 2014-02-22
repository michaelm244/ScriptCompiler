

var express = require('express'),
	fs = require('fs'),
	exec = require('child_process').exec;

var app = express();

app.use(express.bodyParser());

app.post('/', function(req, res) {
	console.log("got post request");
	var lang = req.body.language;
	var code = req.body.code;
	if(lang == 'java') {
		fs.writeFile(__dirname+'/Code.java', code, function(err) {
			if(err)
				console.log(err);
			else {
				console.log("Code.java saved succesfully");
				exec('javac Code.java && java Code', function(err, out, stderr) {
					res.send(out);
				});
			}
		});
	}
	else if(lang == 'python') {
		fs.writeFile(__dirname+'/code.py', code, function(err) {
			if(err)
				console.log(err);
			else {
				console.log("code.py saved succesfully");
				exec('python code.py', function(err, out, stderr) {
					res.send(out);
				});
			}
		});
	}
	else if(lang == 'Javascript') {
		fs.writeFile(__dirname+'/code.js', code, function(err) {
			if(err)
				console.log(err);
			else {
				console.log("code.js saved succesfully");
				exec('node code.js', function(err, out, stderr) {
					res.send(out);
				});
			}
		});
	}
});

app.listen(3000);