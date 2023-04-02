import os
from flask import *
from werkzeug.utils import secure_filename

app=Flask(__name__)
app.secret_key='111'
from src.dbconnectionnew import *



@app.route('/login_code', methods = ['get','post'] )
def login_code():
    username =request.form['uname']
    password =request.form['pword']

    ary="select * from login where username =%s and password =%s"
    val =(username,password)

    res = selectone(ary,val)
    if res is None:
        return jsonify({'task': 'invalid'})

    else:
        id = res[0]
        return jsonify({'task': 'success', 'lid': id})


@app.route('/viewstudents',methods=['POST'])
def viewstudents():
    qry="SELECT * FROM `student`  "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ttviewtimetable',methods=['POST'])
def ttviewtimetable():
    qry="SELECT * FROM `timetable` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ssviewtimetable',methods=['POST'])
def ssviewtimetable():
    qry="SELECT * FROM `timetable` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ttviewtassignment',methods=['POST'])
def ttviewtassignment():
    qry="SELECT * FROM `assignment` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ssviewtassignment',methods=['POST'])
def ssviewtassignment():
    qry="SELECT * FROM `assignment` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ttviewtinternal',methods=['POST'])
def ttviewtinternal():
    qry="SELECT * FROM `internal marks` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ssviewtinternal',methods=['POST'])
def ssviewtinternal():
    qry="SELECT * FROM `internal marks` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ttviewtexam',methods=['POST'])
def ttviewtexam():
    qry="SELECT * FROM `exam` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ssviewtexam',methods=['POST'])
def ssviewtexam():
    qry="SELECT * FROM `exam` "
    res= selectall(qry)
    return jsonify(res)


@app.route('/ttviewstudymat',methods=['POST'])
def ttviewstudymat():
    qry="SELECT * FROM `materials` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ssviewstudymat',methods=['POST'])
def ssviewstudymat():
    qry="SELECT * FROM `materials` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ttviewfeedback',methods=['POST'])
def ttviewfeedback():
    qry="SELECT * FROM `feedback` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ssviewfeedback',methods=['POST'])
def ssviewfeedback():
    qry="SELECT * FROM `feedback` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ttviewsurvey',methods=['POST'])
def ttviewsurvey():
    qry="SELECT * FROM `survey` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ssviewsurvey',methods=['POST'])
def ssviewsurvey():
    qry="SELECT * FROM `survey` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ttviewattendence',methods=['POST'])
def ttviewattendence():
    qry="SELECT * FROM `attendence` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ssviewattendence',methods=['POST'])
def ssviewattendence():
    qry="SELECT * FROM `attendence` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ttviewprofile',methods=['POST'])
def ttviewprofile():
    qry="SELECT * FROM `teacher` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ssviewprofile',methods=['POST'])
def ssviewprofile():
    qry="SELECT * FROM `student` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ttviewchat',methods=['POST'])
def ttviewchat():
    qry="SELECT * FROM `chat` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ssviewchat',methods=['POST'])
def ssviewchat():
    qry="SELECT * FROM `chat` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/viewannoucement',methods=['POST'])
def viewannoucement():
    qry="SELECT * FROM `anoucement` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/viewfee',methods=['POST'])
def viewfee():
    qry="SELECT * FROM `fee` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ssviewchat',methods=['POST'])
def ssviewchat():
    qry="SELECT * FROM `chat` "
    res= selectall(qry)
    return jsonify(res)


@app.route('/addasignment',methods=['POST'])
def addasignment():
    qry="SELECT * FROM `assignment` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/postasignment',methods=['POST'])
def postasignment():
    qry="SELECT * FROM `assignment` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/addinternal',methods=['POST'])
def addinternal():
    qry="SELECT * FROM `internal marks` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/addexam',methods=['POST'])
def addexam():
    qry="SELECT * FROM `exam` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/postexam',methods=['POST'])
def postexam():
    qry="SELECT * FROM `exam` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/addmaterials',methods=['POST'])
def addmaterials():
    qry="SELECT * FROM `materials` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/addattendence',methods=['POST'])
def addattendence():
    qry="SELECT * FROM `attendence` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ttupdateprofile',methods=['POST'])
def ttupdateprofile():
    qry="SELECT * FROM `teacher` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ssupdateprofile',methods=['POST'])
def ssupdateprofile():
    qry="SELECT * FROM `student` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ssupdateprofile',methods=['POST'])
def ssupdateprofile():
    qry="SELECT * FROM `student` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ttsendchat',methods=['POST'])
def ttsendchat():
    qry="SELECT * FROM `chat` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/sssendchat',methods=['POST'])
def sssendchat():
    qry="SELECT * FROM `chat` "
    res= selectall(qry)
    return jsonify(res)


@app.route('/postfeedback',methods=['POST'])
def postfeedback():
    qry="SELECT * FROM `feedback` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/postsurvey',methods=['POST'])
def postsurvey():
    qry="SELECT * FROM `survey` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/postsurvey',methods=['POST'])
def postsurvey():
    qry="SELECT * FROM `survey` "
    res= selectall(qry)
    return jsonify(res)





