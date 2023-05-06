import os
from flask import *
from werkzeug.utils import secure_filename

app=Flask(__name__)
app.secret_key='111'
from src.dbconnectionnew import *
import functools

def login_required(func):
    @functools.wraps(func)
    def secure_function():
        if "lid" not in session:
            return render_template('loginindex.html')
        return func()

    return secure_function


@app.route('/logout')
def logout():
    session.clear()
    return redirect('/')

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
        session['lid']=res['login id']
        return redirect('/hod')

    else:
        return '''<script> alert ("password");window.location ="/" </script>'''



@app.route('/hod')
@login_required
def hod():
    return render_template("hodindex.html")


@app.route('/timetable')
@login_required
def timetable():
    return render_template("/timetable.html")


@app.route('/tt1',methods=['post'])
@login_required
def tt1():
    sem=request.form['s']
    qry="SELECT `time_table`.*,`subject`.`subject name`  FROM `subject` JOIN `time_table` ON `time_table`.`sub_id`=`subject`.`subj_id` WHERE `time_table`.`semester`=%s"
    res=selectall2(qry,sem)
    session['sem']=sem
    if len(res)==0:
        qry="SELECT * FROM `subject` WHERE `semester`=%s"
        r=selectall2(qry,sem)
        session['sem']=sem
        day=['Monday','Tuesday','Wednesday','Thursday','Friday']

        return render_template("/addtimetable.html",val=r,day=day)
    else:
        day = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday']
        result=[["Day/Hour",1,2,3,4,5,6]]
        for i in day:
            row=[i]
            qry="SELECT `subject`.`subject name`,`time_table`.* FROM `time_table` JOIN `subject` ON `subject`.`subj_id`=`time_table`.`sub_id`  WHERE `time_table`.`day`=%s AND `time_table`.`semester`=%s  ORDER by `hours`"
            rr=selectall2(qry,(i,sem))
            for iii in rr:
                row.append(iii['subject name'])
            result.append(row)
        return render_template("/viewtimetable.html",rr=result)


@app.route('/ttedit',methods=['post'])

@login_required
def ttedit():
    sem=session['sem']
    qry="SELECT `time_table`.*,`subject`.`subject name`  FROM `subject` JOIN `time_table` ON `time_table`.`sub_id`=`subject`.`subj_id` WHERE `time_table`.`semester`=%s"
    res=selectall2(qry,sem)
    session['sem']=sem

    day = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday']
    result=[["Day/Hour",1,2,3,4,5,6]]
    for i in day:
        row=[i]
        qry="SELECT `subject`.subj_id,`subject`.`subject name`,`time_table`.* FROM `time_table` JOIN `subject` ON `subject`.`subj_id`=`time_table`.`sub_id`  WHERE `time_table`.`day`=%s AND `time_table`.`semester`=%s  ORDER by `hours`"
        rr=selectall2(qry,(i,sem))
        for iii in rr:
            row.append(iii['subj_id'])
        result.append(row)
        print(result)
    qry = "SELECT *  FROM `subject` where `semester`=%s"
    res = selectall2(qry, sem)
    return render_template("/updatetimetable.html",rr=result,r=res)



@app.route('/tt2',methods=['post'])
@login_required
def tt2():
        day=['Monday','Tuesday','Wednesday','Thursday','Friday']
        for i in day:
            for j in range(0,6):
                sub=request.form[i+str(j)]
                qry="INSERT INTO `time_table` VALUES(NULL,%s,%s,%s,%s)"
                val=(sub,session['sem'],i,j+1)
                iud(qry,val)
        return '''<script>alert("added")</script>'''



@app.route('/tt_update',methods=['post'])
@login_required
def tt_update():
        day=['Monday','Tuesday','Wednesday','Thursday','Friday']
        for i in day:
            for j in range(0,6):
                sub=request.form[i+str(j)]
                qry="UPDATE `time_table` SET `sub_id`=%s WHERE `semester`=%s AND `day`=%s AND `hours`=%s"
                val=(sub,session['sem'],i,j+1)
                iud(qry,val)
        return '''<script>alert("Updated");window.location='timetable'</script>'''


@app.route('/addfees',methods=['post'])
@login_required
def addfees():
    return render_template("add fee.html")

@app.route('/indexfor',methods=['post'])
@login_required
def indexfor():
    return render_template("indexfor allll.html")

@app.route('/addfees1',methods=['post'])
@login_required
def addfees1():
    title = request.form['textfield22']
    sem = request.form['select']
    amount = request.form['textfield']
    ladate = request.form['textfield2']
    desc = request.form['textfield3']
    qr = "INSERT INTO `fee` VALUES(NULL,%s,%s,%s,%s,%s)"
    va = (sem, amount, ladate, desc,title)
    iud(qr, va)
    return '''<script>alert("success");window.location="/viewfee#about"</script>'''


@app.route('/addfeedettails',methods=['post'])
@login_required
def addfeedetails():
    qry="SELECT * FROM `fee`"
    res=selectall(qry)
    qr="SELECT * FROM `student`"
    re=selectall(qr)
    return render_template("add fee details.html",val=res,v=re)

@app.route('/addfeedettails1',methods=['post'])
@login_required
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
    return '''<script>alert("success");window.location="/viewfeedetails#about"</script>'''


@app.route('/addstudent',methods=['post'])
@login_required
def addstudent():
    name = request.form['textfield']
    addno = request.form['textfield2']
    sem = request.form['select']
    gender = request.form['radiobutton']
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
    qr = "INSERT INTO `student` VALUES(NULL,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
    va = (str(id), name, addno, sem, gender,dob,address, phone, jointdate,img)
    iud(qr, va)
    return '''<script>alert("success");window.location="/viewstudents#about"</script>'''


@app.route('/addst',methods=['get','post'])
@login_required
def addst():
    return render_template("add student.html")

@app.route('/addteacher',methods=['post'])
@login_required
def addteacher():
    return render_template("add teacher.html")


@app.route('/addteacher1',methods=['post'])
@login_required
def addteacher1():
   name=request.form['textfield']
   quali=request.form['textfield2']
   desig=request.form['textfield3']
   gender = request.form['radiobutton']
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
   va=(str(id),name,quali,desig,gender,age,phone,img)
   iud(qr,va)
   return '''<script>alert("success");window.location="/viewteacher#about"</script>'''


@app.route('/addsubject',methods=['post'])
@login_required
def addsubject():
    return render_template("add subject.html")

@app.route('/addsubject1',methods=['post'])
@login_required
def addsubject1():
    subjname = request.form['textfield']
    sem = request.form['select']
    crd = request.form['textfield3']
    qr = "INSERT INTO `subject` VALUES(NULL,%s,%s,%s)"
    va = (subjname, sem, crd)
    iud(qr, va)
    return '''<script>alert("success");window.location="/viewsubject#about"</script>'''

@app.route('/addanoucement',methods=['post'])
@login_required
def addanoucement():
    return render_template("addanoucement.html")

@app.route('/addanoucement1',methods=['post'])
@login_required
def addanoucement1():
    an = request.form['textfield']
    qr = "INSERT INTO `anoucement` VALUES(NULL,%s,curdate())"
    va = (an)
    iud(qr, va)
    return '''<script>alert("success");window.location="/viewannouncement#about"</script>'''

@app.route('/viewannouncement')
@login_required
def viewannouncement():
    qry = "SELECT * FROM anoucement "
    res = selectall(qry)
    return render_template("viewannouncement.html",val=res)

@app.route('/viewsubject')
@login_required
def viewsubject():
    qry = "SELECT * FROM subject "
    res = selectall(qry)
    return render_template("view subject.html",val=res)

@app.route('/viewfee')
@login_required
def viewfee():
    qry = "SELECT * FROM fee"
    res = selectall(qry)
    return render_template("view fee.html",val=res)

@app.route('/viewfeedetails')
@login_required
def viewfeedetails():
     qry = "SELECT * FROM `fee details` JOIN `fee` ON `fee details`.f_id=`fee`.f_id JOIN `student` ON `fee details`.stud_id=`student`.login_id"
     res=selectall(qry)
     return render_template("view fee details.html",val=res)

@app.route('/viewteacher')
@login_required
def viewteacher():
    qry="SELECT * FROM teacher "
    res=selectall(qry)
    return render_template("view teacher.html",val=res)

@app.route('/viewstudents')
@login_required
def viewstudents():
    qry = "SELECT * FROM student"
    res = selectall(qry)
    return render_template("view students.html",val=res)

@app.route('/viewfeedback')
@login_required
def viewfeedback():
    qry = "SELECT * FROM `teacher`"
    res = selectall(qry)
    return render_template("view feedback.html",val=res)


@app.route('/viewfeedback1',methods=['post'])
@login_required
def viewfeedback1():
    sid=request.form['select']
    qry ="SELECT * FROM `feedback` WHERE`staff_id`=%s "
    res = selectall2(qry,sid)
    q = "SELECT * FROM `teacher`"
    r = selectall(q)
    return render_template("view feedback.html",v=res,val=r)




@app.route('/viewsurvey')
@login_required
def viewsurvey():
    qry = "SELECT * FROM `survey`"
    res = selectall(qry)
    return render_template("view survey.html",val=res)

@app.route('/viewassignsub')
@login_required
def viewassignsub():
    qry ="SELECT  `assignsub`.*,`teacher`.`name`,`subject`.`subject name` FROM `assignsub` JOIN `subject`ON `subject`.`subj_id`=`assignsub`.`sub_id` JOIN `teacher`ON `teacher`.`login_id`=`assignsub`.`t_id`"
    res = selectall(qry)
    return render_template("viewassignsub.html",val=res)

@app.route('/assignsub',methods=['post'])
@login_required
def assignsub():
    qry = "SELECT * FROM `teacher`"
    res = selectall(qry)
    qry = "SELECT * FROM `subject` WHERE `subj_id` NOT IN(SELECT `sub_id` FROM `assignsub`)"
    res1 = selectall(qry)
    return render_template("assignsub.html",data=res1,val=res)

@app.route('/assignsub1',methods=['post'])
@login_required
def assignsub1():
    teachername = request.form['select']
    sub = request.form['select2']
    qr = "INSERT INTO `assignsub` VALUES(NULL,%s,%s)"
    va = ( teachername,sub)
    iud(qr, va)
    return '''<script>alert("success");window.location="/viewassignsub#about"</script>'''


@app.route('/editteacher')
@login_required
def editteacher():
    id=request.args.get('id')
    session['tid']=id
    qry = "select * from `teacher` where  login_id=%s"
    res= selectone(qry,id)
    return render_template("editteacher.html",val=res)


@app.route('/deleteteacher')
@login_required
def deleteteacher():
    id=request.args.get('id')
    qry = "delete from `teacher` where  login_id=%s"
    iud(qry,id)
    return  '''<script>alert("success");window.location="/viewteacher#about"</script>'''


@app.route('/updateteacher',methods=['post'])
@login_required
def updateteacher():
    try:
       name=request.form['textfield']
       quali=request.form['textfield2']
       desig=request.form['textfield3']
       gender = request.form['radiobutton']
       age = request.form['textfield32']
       phone = request.form['textfield33']
       photo = request.files['file']
       img= secure_filename(photo.filename)
       photo.save(os.path.join('static/photos',img))
       qry = "UPDATE teacher SET name=%s,qualification=%s,designation=%s,gender=%s,age=%s,phone=%s,photo=%s where login_id=%s"
       val=(name,quali,desig,gender,age,phone,img,session['tid'])
       iud(qry,val)
       return '''<script>alert("success");window.location="/viewteacher#about"</script>'''
    except Exception as e:
        name = request.form['textfield']
        quali = request.form['textfield2']
        desig = request.form['textfield3']
        gender = request.form['radiobutton']
        age = request.form['textfield32']
        phone = request.form['textfield33']
        qry = "UPDATE teacher SET name=%s,qualification=%s,designation=%s,gender=%s,age=%s,phone=%s where login_id=%s"
        val = (name, quali, desig, gender, age, phone, session['tid'])
        iud(qry, val)
        return '''<script>alert("success");window.location="/viewteacher#about"</script>'''


@app.route('/editstudent')
@login_required
def editstudent():
    id=request.args.get('id')
    session['stid']=id
    qry = "select * from `student` where  login_id=%s"
    res= selectone(qry,id)
    return render_template("editstudent.html",val=res)

@app.route('/deletestudent')
@login_required
def deletestudent():
    id=request.args.get('id')
    qry = "delete from `student` where  login_id=%s"
    iud(qry,id)
    return  '''<script>alert("success");window.location="/viewstudents#about"</script>'''

@app.route('/updatestudent',methods=['post'])
@login_required
def updatestudent():
    try:
        name = request.form['textfield']
        addno = request.form['textfield2']
        sem = request.form['select']
        gender = request.form['radiobutton']
        dob = request.form['textfield33']
        address = request.form['textfield34']
        phone = request.form['textfield35']
        jointdate = request.form['textfield36']
        photo = request.files['file']
        img = secure_filename(photo.filename)
        photo.save(os.path.join('static/photos', img))
        qry = "UPDATE student SET name=%s,`addmission no`=%s,smester=%s,gender=%s,dob=%s,address=%s,phone=%s,jointdate=%s,photo=%s where login_id=%s"
        val=(name,addno,sem,gender,dob,address,phone,jointdate,img,session['stid'])
        iud(qry,val)
        return '''<script>alert("success");window.location="/viewstudents#about"</script>'''
    except Exception as e:
        name = request.form['textfield']
        addno = request.form['textfield2']
        sem = request.form['select']
        gender = request.form['radiobutton']
        dob = request.form['textfield33']
        address = request.form['textfield34']
        phone = request.form['textfield35']
        jointdate = request.form['textfield36']
        qry = "UPDATE student SET name=%s,`addmission no`=%s,smester=%s,gender=%s,dob=%s,address=%s,phone=%s,jointdate=%s where login_id=%s"
        val = (name, addno, sem, gender, dob, address, phone, jointdate, session['stid'])
        iud(qry, val)
        return '''<script>alert("success");window.location="/viewstudents#about"</script>'''

@app.route('/editsubject')
@login_required
def editsubject():
    id=request.args.get('id')
    session['sid']=id
    qry = "select * from `subject` where  subj_id=%s"
    res= selectone(qry,id)
    return render_template("editsubject.html",val=res)

@app.route('/deletesubject')
@login_required
def deletesubject():
    id=request.args.get('id')
    qry = "delete from `subject` where  subj_id=%s"
    iud(qry,id)
    return  '''<script>alert("success");window.location="/viewsubject#about"</script>'''

@app.route('/updatesubject',methods=['post'])
@login_required
def updatesubject():
    subjname = request.form['textfield']
    sem = request.form['select']
    crd = request.form['textfield3']
    qry = "UPDATE subject SET `subject name`=%s,semester=%s,credit=%s where subj_id=%s"
    val=(subjname,sem,crd,session['sid'])
    iud(qry,val)
    return '''<script>alert("success");window.location="/viewsubject#about"</script>'''

@app.route('/editassignsub')
@login_required
def editassignsub():
    id=request.args.get('id')
    session['asid']=id
    qry = "select * from `assignsub` where  assign_id=%s"
    ress= selectone(qry,id)
    qry = "SELECT * FROM `teacher`"
    res = selectall(qry)
    qry = "SELECT * FROM `subject`"
    res1 = selectall(qry)

    return render_template("editassignsub.html",vall=ress,val=res,data=res1)

@app.route('/deleteassignsub')
@login_required
def deleteassignsub():
    id=request.args.get('id')
    qry = "delete from `assignsub` where  assign_id=%s"
    iud(qry,id)
    return  '''<script>alert("success");window.location="/viewassignsub#about"</script>'''

@app.route('/updateassignsub',methods=['post'])
@login_required
def updateassignsub():
    teachername = request.form['select']
    sub = request.form['select2']
    qry = "UPDATE assignsub SET t_id=%s,sub_id=%s where assign_id=%s"
    val=(teachername,sub,session['asid'])
    iud(qry,val)
    return '''<script>alert("success");window.location="/viewassignsub#about"</script>'''


@app.route('/editfee')
@login_required
def editfee():
    id=request.args.get('id')
    session['fid']=id
    qry = "select * from `fee` where  f_id=%s"
    res= selectone(qry,id)
    return render_template("editfee.html",val=res)

@app.route('/deletefee')
@login_required
def deletefee():
    id=request.args.get('id')
    qry = "delete from `fee` where  f_id=%s"
    iud(qry,id)
    return  '''<script>alert("success");window.location="/viewfee#about"</script>'''

@app.route('/updatefee',methods=['post'])
@login_required
def updatefee():
    sem = request.form['select']
    amount = request.form['textfield']
    ladate = request.form['textfield2']
    desc = request.form['textfield3']
    title = request.form['textfield22']
    qry = "UPDATE fee SET semester=%s,amount=%s,lastdate=%s,description=%s,title=%s where f_id=%s"
    val=(sem,amount,ladate,desc,title,session['fid'])
    iud(qry,val)
    return '''<script>alert("success");window.location="/viewfee#about"</script>'''


@app.route('/deletefeedetails')
def deletefeedetails():
    id=request.args.get('id')
    qry = "delete from `fee` where  fd_id=%s"
    iud(qry,id)
    return  '''<script>alert("success");window.location="/viewfeedetails#about"</script>'''


@app.route('/editfeedetails')
@login_required
def editfeedetails():
    id=request.args.get('id')
    session['fdid']=id
    qry = "select * from `fee details` where  fd_id=%s"
    res = selectone(qry,id)
    qry = "SELECT * FROM `fee`"
    ress = selectall(qry)
    qr = "SELECT * FROM `student`"
    re = selectall(qr)
    return render_template("editfeedetails.html", val1=ress, v=re ,val=res)


@app.route('/updatefeedetails',methods=['post'])
@login_required
def updatefeedetails():
    title = request.form['select2']
    stdname = request.form['select3']
    amtp = request.form['textfield2']
    amtd = request.form['textfield3']
    datep = request.form['textfield4']
    status = request.form['textfield5']
    qry = "UPDATE `fee details` SET `f_id`=%s,`stud_id`=%s,`amount paid`=%s,`amount due`=%s,`date paid`=%s,`status`=%s Where `fd_id`=%s"
    val=(title,stdname,amtp,amtd,datep,status,session['fdid'])
    iud(qry,val)
    return '''<script>alert("success");window.location="/viewfeedetails#about"</script>'''

@app.route('/editanoucement')
@login_required
def editanoucement():
    id=request.args.get('id')
    session['anid']=id
    qry = "select * from `anoucement` where  an_id=%s"
    res = selectone(qry,id)
    return render_template("edit anoucement.html", val=res)


@app.route('/updatanoucement',methods=['post'])
@login_required
def updatanoucement():
    an = request.form['textfield']
    qry = "UPDATE `anoucement` SET `anouncement`=%s where `an_id`=%s"
    val=(an,session['anid'])
    iud(qry,val)
    return '''<script>alert("success");window.location="/viewannouncement#about"</script>'''

@app.route('/deleteanoucement')
@login_required
def deleteanoucement():
    id=request.args.get('id')
    qry = "delete from `anoucement` where  an_id=%s"
    iud(qry,id)
    return  '''<script>alert("success");window.location="/viewannouncement#about"</script>'''

app.run(debug=True)