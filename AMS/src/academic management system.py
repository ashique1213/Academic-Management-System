import os
from flask import *
from werkzeug.utils import secure_filename

app=Flask(__name__)
app.secret_key='111'
from src.dbconnectionnew import *

@app.route('/')
def login():
    return render_template("loginindex.html")


@app.route('/login_code', methods = ['get','post'] )
def login_code():
    username =request.form['textfield']
    password =request.form['textfield2']

    ary="select * from login where username =%s and password =%s"
    val =(username,password)

    res = selectone(ary,val)

    if res is None:
        return '''<script> alert ("username");window.location ="/" </script>'''

    elif res ['type']== 'admin':
        return redirect('/hod')

    else:
        return '''<script> alert ("password");window.location ="/" </script>'''



@app.route('/hod')
def hod():
    return render_template("hodindex.html")



@app.route('/addfees',methods=['post'])
def addfees():
    return render_template("add fee.html")


@app.route('/addfees1',methods=['post'])
def addfees1():
    title = request.form['textfield22']
    sem = request.form['select']
    amount = request.form['textfield']
    ladate = request.form['textfield2']
    desc = request.form['textfield3']
    qr = "INSERT INTO `fee` VALUES(NULL,%s,%s,%s,%s,%s)"
    va = (sem, amount, ladate, desc,title)
    iud(qr, va)
    return '''<script>alert("success");window.location="/viewfee"</script>'''


@app.route('/addfeedettails',methods=['post'])
def addfeedetails():
    qry="SELECT * FROM `fee`"
    res=selectall(qry)
    qr="SELECT * FROM `student`"
    re=selectall(qr)
    return render_template("add fee details.html",val=res,v=re)

@app.route('/addfeedettails1',methods=['post'])
def addfeedetails1():
    title = request.form['select2']
    stdname = request.form['select3']
    # sem = request.form['select']
    amtp = request.form['textfield2']
    amtd = request.form['textfield3']
    datep = request.form['textfield4']
    status =request.form['textfield5']
    qr = "INSERT INTO `fee details` VALUES(NULL,%s,%s,%s,%s,%s,%s)"
    va = (title,stdname, amtp, amtd,datep,status)
    iud(qr, va)
    return '''<script>alert("success");window.location="/viewfeedetails"</script>'''


@app.route('/addstudent',methods=['post'])
def addstudent():
    name = request.form['textfield']
    addno = request.form['textfield2']
    sem = request.form['select']
    sex = request.form['radiobutton']
    age = request.form['textfield32']
    dob = request.form['textfield33']
    address = request.form['textfield34']
    phone = request.form['textfield35']
    photo=request.files['file']
    img = secure_filename(photo.filename)
    photo.save(os.path.join('static/photos', img))
    jointdate = request.form['textfield36']
    username = request.form['textfield4']
    password = request.form['textfield5']
    qry = "insert into login values(null,%s,%s,'student')"
    val = (username, password)
    id = iud(qry, val)
    qr = "INSERT INTO `student` VALUES(NULL,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
    va = (str(id), name, addno, sem, sex, age,dob,address, phone, jointdate,img)
    iud(qr, va)
    return '''<script>alert("success");window.location="/viewstudents"</script>'''


@app.route('/addst',methods=['get','post'])
def addst():
    return render_template("add student.html")

@app.route('/addteacher',methods=['post'])
def addteacher():
    return render_template("add teacher.html")


@app.route('/addteacher1',methods=['post'])
def addteacher1():
   name=request.form['textfield']
   quali=request.form['textfield2']
   desig=request.form['textfield3']
   sex = request.form['radiobutton']
   age = request.form['textfield32']
   phone = request.form['textfield33']
   photo = request.files['file']
   img= secure_filename(photo.filename)
   photo.save(os.path.join('static/photos',img))
   username = request.form['textfield4']
   password = request.form['textfield5']
   qry="insert into login values(null,%s,%s,'teacher')"
   val=(username,password)
   id=iud(qry,val)
   qr="INSERT INTO `teacher` VALUES(NULL,%s,%s,%s,%s,%s,%s,%s,%s)"
   va=(str(id),name,quali,desig,sex,age,phone,img)
   iud(qr,va)
   return '''<script>alert("success");window.location="/viewteacher"</script>'''



@app.route('/addsubject',methods=['post'])
def addsubject():
    return render_template("add subject.html")

@app.route('/addsubject1',methods=['post'])
def addsubject1():
    subjname = request.form['textfield']
    sem = request.form['select']
    crd = request.form['textfield3']
    qr = "INSERT INTO `subject` VALUES(NULL,%s,%s,%s)"
    va = (subjname, sem, crd)
    iud(qr, va)
    return '''<script>alert("success");window.location="/viewsubject"</script>'''



@app.route('/viewsubject')
def viewsubject():
    qry = "SELECT * FROM subject "
    res = selectall(qry)
    return render_template("view subject.html",val=res)

@app.route('/viewfee')
def viewfee():
    qry = "SELECT * FROM fee"
    res = selectall(qry)
    return render_template("view fee.html",val=res)

@app.route('/viewfeedetails')
def viewfeedetails():
     qry = "SELECT * FROM `fee details` JOIN `fee` ON `fee details`.f_id=`fee`.f_id JOIN `student` ON `fee details`.stud_id=`student`.login_id"
     res=selectall(qry)
     return render_template("view fee details.html",val=res)

@app.route('/viewteacher')
def viewteacher():
    qry="SELECT * FROM teacher "
    res=selectall(qry)
    return render_template("view teacher.html",val=res)

@app.route('/viewstudents')
def viewstudents():
    qry = "SELECT * FROM student"
    res = selectall(qry)
    return render_template("view students.html",val=res)

@app.route('/viewfeedback')
def viewfeedback():
    qry = "SELECT * FROM feed_response JOIN SUBJECT ON feed_response .sub_id=`subject`.subj_id"
    res = selectall(qry)
    return render_template("view feedback.html",val=res)

@app.route('/viewsurvey')
def viewsurvey():
    qry = "SELECT * FROM survey_response JOIN SUBJECT ON survey_response .sub_id=`subject`.subj_id"
    res = selectall(qry)
    return render_template("view survey.html",val=res)

@app.route('/viewassignsub')
def viewassignsub():
    qry ="SELECT  `assignsub`.*,`teacher`.`name`,`subject`.`subject name` FROM `assignsub` JOIN `subject`ON `subject`.`subj_id`=`assignsub`.`sub_id` JOIN `teacher`ON `teacher`.`login_id`=`assignsub`.`t_id`"
    res = selectall(qry)
    return render_template("viewassignsub.html",val=res)

@app.route('/assignsub',methods=['post'])
def assignsub():
    qry = "SELECT * FROM `teacher`"
    res = selectall(qry)
    qry = "SELECT * FROM `subject`"
    res1 = selectall(qry)
    return render_template("assignsub.html",data=res1,val=res)

@app.route('/assignsub1',methods=['post'])
def assignsub1():
    teachername = request.form['select']
    sub = request.form['select2']
    qr = "INSERT INTO `assignsub` VALUES(NULL,%s,%s)"
    va = ( teachername,sub)
    iud(qr, va)
    return '''<script>alert("success");window.location="/viewassignsub"</script>'''


@app.route('/editteacher')
def editteacher():
    id=request.args.get('id')
    session['tid']=id
    qry = "select * from `teacher` where  login_id=%s"
    res= selectone(qry,id)
    return render_template("editteacher.html",val=res)


app.run(debug=True)