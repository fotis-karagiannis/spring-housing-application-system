const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');
const requestToApi = require('request');

const app = express();
app.use(express.static(path.join(__dirname, 'public')));
app.use(bodyParser.urlencoded({ extended: true }));

var currentUsername;

app.get('/', function (req, res) {
    res.sendFile(path.join(__dirname + '/public/pages/welcome.html'));
});

app.get('/login', function (req, res) {
    res.sendFile(path.join(__dirname + '/public/pages/login.html'));
});

app.get('/submitForm', function (req, res) {
    res.sendFile(path.join(__dirname + '/public/pages/submitForm.html'));
});

app.get('/showForm', function (req, res) {
    res.sendFile(path.join(__dirname + '/public/pages/showForm.html'));
});

app.get('/showRating', function (req, res) {
    res.sendFile(path.join(__dirname + '/public/pages/showRating.html'));
});

app.get('/home', function (req, res) {
    res.sendFile(path.join(__dirname + '/public/pages/home.html'));
});

app.get('/about', function (req, res) {
    res.sendFile(path.join(__dirname + '/public/pages/info.html'));
});

app.post('/showForm', async (req, res) => {
    var getFormRequest = {
        'method': 'GET',
        'url': 'http://localhost:8080/ES_Backend/api/user/getForm/' + currentUsername,
        'headers': {
        }
    };

    const getFormResult = new Promise((resolve, reject) => {
        requestToApi(getFormRequest, function (error, response) {
            if (error) return reject(error);
            return resolve(response.body);
        });
    });

    try {
        var form_obj = await getFormResult;
        var n = JSON.parse(form_obj);
		res.send(n);
    } catch (error) {
        console.log(error);
    }
});

app.post('/showRating', async (req, res) => {
    var getFormRequest = {
        'method': 'GET',
        'url': 'http://localhost:8080/ES_Backend/api/user/getFormRating/' + currentUsername,
        'headers': {
        }
    };

    const getFormResult = new Promise((resolve, reject) => {
        requestToApi(getFormRequest, function (error, response) {
            if (error) return reject(error);
            return resolve(response.body);
        });
    });

    try {
        var form_obj = await getFormResult;
        var n = JSON.parse(form_obj);
		res.send(n);
    } catch (error) {
        console.log(error);
    }
});

app.post('/login', async (req, res) => {

    var loginRequest = {
        'method': 'POST',
        'url': 'http://localhost:8080/ES_Backend/api/user/getUser',
        'headers': {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        form: {
            'username': req.body.username, //from the form
            'password': req.body.password
        }
    };

    const loginResult = new Promise((resolve, reject) => {
        requestToApi(loginRequest, function (error, response) {
            if (error) return reject(error);
            return resolve(response.body);
        });
    });

    try {
        var user_obj = JSON.parse(await loginResult);
        if (user_obj.user == 'FOUND') {
			currentUsername = req.body.username;
            res.redirect('/home');
        } else {
            res.redirect('/login?error=' + encodeURIComponent('Incorrect_Credential'));
        }
    } catch (ex) {
        console.log(ex);
    }

});

app.post('/submitForm', async (req, res) => {
    var submitFormRequest = {
        'method': 'POST',
        'url': 'http://localhost:8080/ES_Backend/api/user/submitForm',
        'headers': {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        form: {
            'username': currentUsername,
            'personal_income': req.body.personal_income,
            'family_income': req.body.family_income,
            'siblings_studying': req.body.siblings_studying,
            'home_city': req.body.home_city,
            'year_studying': req.body.year_studying,
            'year_staying': req.body.year_staying,
            'unemployed_parents': req.body.unemployed_parents
        }
    };

    var checkCanApplyRequest = {
        'method': 'GET',
        'url': 'http://localhost:8080/ES_Backend/api/user/checkCanApply/' + currentUsername,
        'headers': {
        }
    };

    /**Takes result from checkCanApplyRequest */
    const checkCanApplyResult = new Promise((resolve, reject) => {
        requestToApi(checkCanApplyRequest, function (error, response) {
            if (error) return reject(error);
            return resolve(response.body);
        });
    });

    try {
        var check = JSON.parse(await checkCanApplyResult);
        if (check.user == 'CAN_APPLY') {

            const submitFormResult = new Promise((resolve, reject) => {
                requestToApi(submitFormRequest, function (error, response) {
                    if (error) return reject(error);
                    return resolve(response.body);
                });
            });

            var form_obj = JSON.parse(await submitFormResult);
            if (form_obj.form == 'SUBMITED') {
                res.redirect('/submitForm?form=' + encodeURIComponent('submited'));
            } else {
                res.redirect('/submitForm?form=' + encodeURIComponent('not_submited'));
            }

        } else if (check.user == 'CANT_APPLY') {
            res.redirect('/submitForm?user=' + encodeURIComponent('no_right_to_apply'));
        }
    } catch (error) {
        console.log(error);
    }
    res.end();
});

app.listen(process.env.port || 3000, function () {
    console.log(`now listening for request in port: ${process.env.port || 3000}`);
});
