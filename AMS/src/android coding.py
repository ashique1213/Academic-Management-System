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
        id = res['login id']
        type = res['type']
        return jsonify({'task': 'success', 'lid': id,'type':type})




@app.route('/viewstudents',methods=['POST'])
def viewstudents():
    year=request.form['year']
    qry="SELECT * FROM `student` where year(jointdate)=%s "
    res= selectall2(qry,year)
    return jsonify(res)


@app.route('/viewstudents1',methods=['POST'])
def viewstudents1():
    sid=request.form['sid']


    qry="SELECT * FROM `student` WHERE `smester` IN(SELECT `semester` FROM `subject` WHERE `subj_id`=%s) "
    res= selectall2(qry,sid)
    return jsonify(res)


@app.route('/viewstudentss',methods=['POST'])
def viewstudentss():



    qry="SELECT * FROM `student` "
    res= selectall(qry)
    return jsonify(res)



@app.route('/ttviewtimetable',methods=['POST'])
def ttviewtimetable():
    qry="SELECT * FROM `timetable` "
    res= selectall(qry)
    return jsonify(res)


@app.route('/ttviewtassignment',methods=['POST'])
def ttviewtassignment():
    lid=request.form['lid']
    qry="SELECT * FROM `assignment` where staf_id=%s"
    res= selectall2(qry,lid)
    return jsonify(res)



@app.route('/ttviewtassignment1',methods=['POST'])
def ttviewtassignment1():

    qry="SELECT * FROM `assignment` WHERE `last date`<=CURDATE()  "
    res= selectall(qry)
    return jsonify(res)



@app.route('/deleeassigment',methods=['POST'])
def deleeassigment():
    lid = request.form['tid']
    qry = "delete from  `assignment` where ass_id=%s"
    iud(qry, lid)
    return jsonify({'task': 'success'})




@app.route('/selectqn',methods=['POST'])
def selectqn():
    lid = request.form['tid']
    qry = "DELETE FROM `questions` WHERE `q_id`=%s"
    iud(qry, lid)
    return jsonify({'task': 'success'})


@app.route('/delectfeed',methods=['POST'])
def delectfeed():
    lid = request.form['tid']
    qry = "DELETE FROM `feedback` WHERE `feed_id`=%s"
    iud(qry, lid)
    return jsonify({'task': 'success'})




@app.route('/delectsurvey',methods=['POST'])
def delectsurvey():
    lid = request.form['tid']
    qry = "DELETE FROM `survey` WHERE `sur_id`=%s"
    iud(qry, lid)
    return jsonify({'task': 'success'})

@app.route('/deleinnn',methods=['POST'])
def deleinnn():
    lid = request.form['tid']
    qry = "delete from  `internal marks` where in_id=%s"
    iud(qry, lid)
    return jsonify({'task': 'success'})

@app.route('/ssviewtassignment',methods=['POST'])
def ssviewtassignment():
    qry="SELECT * FROM `assignment` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ttviewtinternal',methods=['POST'])
def ttviewtinternal():
    lid = request.form['eid']
    qry="SELECT * FROM `internal marks` JOIN `subject`ON `subject`.`subj_id`=`internal marks`.`examid`  JOIN `student`ON `student`.`login_id`=`internal marks`.`stud_id` WHERE `internal marks`.`examid`=%s"
    res= selectall2(qry,lid)
    return jsonify(res)

@app.route('/ssviewtinternal',methods=['POST'])
def ssviewtinternal():
    lid=request.form['lid']
    qry="SELECT * FROM `internal marks` JOIN `subject`ON `subject`.`subj_id`=`internal marks`.`examid`  JOIN `student`ON `student`.`login_id`=`internal marks`.`stud_id`  WHERE  `internal marks`.`stud_id`=%s"
    res= selectall2(qry,lid)
    return jsonify(res)

@app.route('/ttviewtexam',methods=['POST'])
def ttviewtexam():
    qry="SELECT * FROM `exam` JOIN `subject`ON `subject`.`subj_id`=`exam`.`sub_id` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/ssviewtexam',methods=['POST'])
def ssviewtexam():
    qry="SELECT * FROM `exam` "
    res= selectall(qry)
    return jsonify(res)
@app.route('/viewsubject',methods=['POST'])
def viewsubject():
    qry="SELECT * FROM `subject` "
    res= selectall(qry)
    return jsonify(res)

@app.route('/viewsubjectonlyforexam',methods=['POST'])
def viewsubjectonlyforexam():
    lid=request.form['lid']
    qry="SELECT * FROM `subject` JOIN `assignsub` ON  `assignsub`.`sub_id`=`subject`.`subj_id` WHERE `assignsub`.`t_id`=%s"
    res= selectall2(qry,lid)
    print(res)
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
    lid=request.form['lid']
    qry="SELECT * FROM `teacher` where login_id=%s"
    res= selectall2(qry,lid)
    return jsonify(res)
@app.route('/viewstaff',methods=['POST'])
def ttviewstaff():
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
    lid=request.form['lid']
    qry="SELECT * FROM `student` JOIN `chat` ON `chat`.`from_id`=`student`.`login_id` WHERE `chat`.`to_id`=%s "
    res= selectall2(qry,lid)
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





@app.route('/viewfee1',methods=['POST'])
def viewfee1():
    fname = request.form['rid']
    qry="SELECT * FROM `fee details` where f_id=%s"
    res= selectall2(qry,fname)
    return jsonify(res)

@app.route('/viewfeedetails',methods=['POST'])
def viewfeedetails():
    lid=request.form['lid']
    qry="SELECT * FROM `fee` JOIN `student` ON `student`.`smester`=`fee`.`semester` WHERE `student`.`login_id`=%s "
    res= selectall2(qry,lid)
    return jsonify(res)



@app.route('/payfee',methods=['POST'])
def payfee():
    lid=request.form['lid']
    fname = request.form['rid']
    lname = request.form['amt']
    qry="INSERT INTO `fee details` VALUES(NULL,%s,%s,%s,0,CURDATE(),'paid') "
    val=(fname,lid,lname)
    iud(qry,val)
    return jsonify({'task':'success'})



@app.route('/addassigment',methods=['POST'])
def addassigemnt():
    lid=request.form['lid']
    topic=request.form['topic']
    fname = request.form['des']
    lname = request.form['ldate']
    qry="INSERT INTO `assignment` VALUES(NULL,%s,%s,%s,%s,CURDATE())"
    val=(lid,topic,fname,lname)
    iud(qry,val)
    return jsonify({'task':'success'})




@app.route('/addinternal',methods=['POST'])
def addinternal():
    lid=request.form['sid']
    topic=request.form['eid']
    fname = request.form['mark']

    qry="INSERT INTO `internal marks` VALUES(NULL,%s,%s,%s,CURDATE())"
    val=(lid,topic,fname)
    iud(qry,val)
    return jsonify({'task':'success'})

@app.route('/addatten',methods=['POST'])
def addatten():
    print(request.form)
    lid=request.form['sid']
    subid=request.form['eid']
    hour = request.form['hur']
    date = request.form['date']
    att = request.form['att']

    sid = lid.split(',')
    qry="SELECT `login_id` FROM `student` WHERE `smester` IN(SELECT `semester` FROM `subject` WHERE `subj_id`=%s)"
    res=selectall2(qry,subid)
    for ii in res:

        if str(ii['login_id']) in sid:
            qry="INSERT INTO `attendence` VALUES(NULL,%s,%s,%s,%s,%s)"
            val=(ii['login_id'],subid,date,hour,'1')

            iud(qry,val)
        else:
            qry = "INSERT INTO `attendence` VALUES(NULL,%s,%s,%s,%s,%s)"
            val = (ii['login_id'], subid, date, hour, '0')

            iud(qry, val)

    return jsonify({'task':'success'})



@app.route('/timetable',methods=['post','get'])
def timetable():
    return render_template("/foralltimetable.html")


@app.route('/tt1',methods=['post'])
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

        return render_template("/foralladdtimetable.html",val=r,day=day)
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
        return render_template("/forallviewtimetable.html",rr=result)



# @app.route('/ttedit',methods=['post'])
# def ttedit():
#     sem=session['sem']
#     qry="SELECT `time_table`.*,`subject`.`subject name`  FROM `subject` JOIN `time_table` ON `time_table`.`sub_id`=`subject`.`subj_id` WHERE `time_table`.`semester`=%s"
#     res=selectall2(qry,sem)
#     session['sem']=sem
#
#     day = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday']
#     result=[["Day/Hour",1,2,3,4,5,6]]
#     for i in day:
#         row=[i]
#         qry="SELECT `subject`.subj_id,`subject`.`subject name`,`time_table`.* FROM `time_table` JOIN `subject` ON `subject`.`subj_id`=`time_table`.`sub_id`  WHERE `time_table`.`day`=%s AND `time_table`.`semester`=%s  ORDER by `hours`"
#         rr=selectall2(qry,(i,sem))
#         for iii in rr:
#             row.append(iii['subj_id'])
#         result.append(row)
#         print(result)
#     qry = "SELECT *  FROM `subject` where `semester`=%s"
#     res = selectall2(qry, sem)
#     return render_template("/forallupdatetimetable.html",rr=result,r=res)












@app.route('/addexam',methods=['POST'])
def addexam():
    lid=request.form['sid']
    topic=request.form['topic']
    fname = request.form['date']
    timee = request.form['time']
    qry="INSERT INTO `exam`VALUES(NULL,%s,%s,%s,%s)"
    val=(lid,fname,topic,timee)
    iud(qry,val)
    return jsonify({'task':'success'})

@app.route('/viewassigmentnotification',methods=['POST'])
def viewassigmentnotification():
    lis=[]
    qry="SELECT * FROM `assignment` WHERE `curdate`=CURDATE()"
    res= selectonee(qry)
    if res is not None:
        lis.append("assignment")

    qry = "SELECT * FROM `feedback` WHERE `date`=CURDATE()"
    res = selectonee(qry)
    if res is not None:
        lis.append("feedback")
    qry = "SELECT * FROM `survey` WHERE `date`=CURDATE()"
    res = selectonee(qry)
    if res is not None:
        lis.append("survey")

    if len(lis) == 0:
        return jsonify({"task":"no"})
    else:
        txt='#'.join(lis)
        return jsonify({"task":"yes","n":txt})






@app.route('/viewstafftochat',methods=['post'])
def viewfriends():

    qry="SELECT * FROM `teacher` JOIN `login`ON `login`.`login id`=`teacher`.`login_id` WHERE `login`.`type`='teacher'"

    res = selectall(qry)
    return jsonify(res)



@app.route('/viewattendance',methods=['post'])
def viewattendance():
    lid=request.form['lid']
    sid=request.form['sid']
    qry="SELECT COUNT(`attendence`)AS totalworking FROM `attendence` WHERE `stud_id`=%s AND `sub_id`=%s"
    res1 = selectone(qry,(lid,sid))
    print(res1)
    q="SELECT COUNT(`attendence`)AS present FROM `attendence` WHERE `stud_id`=%s AND `sub_id`=%s AND `attendence`=1"
    res2 = selectone(q,(lid,sid))
    q1="SELECT COUNT(`attendence`)AS absent FROM `attendence` WHERE `stud_id`=%s AND `sub_id`=%s AND `attendence`=0"
    res3 = selectone(q1,(lid,sid))
    q="SELECT SUM(`attendence`) AS tpd,COUNT(*) AS twd,(SUM(`attendence`)/COUNT(*))*100 AS per,`subject`.`subject name` FROM `attendence` JOIN `subject` ON `subject`.`subj_id`=`attendence`.`sub_id` WHERE `attendence`.`stud_id`=%s and `attendence`.`sub_id`=%s  "
    res4=selectone(q,(lid,sid))
    return jsonify({"res1":res1['totalworking'],"res2":res2['present'],"res3":res3['absent'],"res4":res4['per']})

@app.route('/staffviewattendance',methods=['post'])
def staffviewattendance():

    sid=request.form['sid']
    # qry="SELECT COUNT(`attendence`)AS totalworking FROM `attendence` WHERE `stud_id`=%s AND `sub_id`=%s"
    # res1 = selectone(qry,(lid,sid))
    # print(res1)
    # q="SELECT COUNT(`attendence`)AS present FROM `attendence` WHERE `stud_id`=%s AND `sub_id`=%s AND `attendence`=1"
    # res2 = selectone(q,(lid,sid))
    # q1="SELECT COUNT(`attendence`)AS absent FROM `attendence` WHERE `stud_id`=%s AND `sub_id`=%s AND `attendence`=0"
    # res3 = selectone(q1,(lid,sid))
    q="SELECT SUM(`attendence`) AS tpd,COUNT(*) AS twd,(SUM(`attendence`)/COUNT(*))*100 AS per,`subject`.`subject name`,`student`.`name` FROM `attendence` JOIN `subject` ON `subject`.`subj_id`=`attendence`.`sub_id` JOIN `student` ON `student`.`login_id`=`attendence`.`stud_id` WHERE `attendence`.`sub_id`=%s GROUP BY `attendence`.`stud_id`  "
    res4=selectall2(q,(sid))
    return jsonify(res4)




@app.route('/ciewsubject1',methods=['post'])
def ciewsubject1():
    ii=request.form['year']
    qry="SELECT * FROM subject where semester=%s"
    res = selectall2(qry,ii)
    return jsonify(res)


@app.route('/ciewsubject',methods=['post'])
def ciewsubject():
    qry="SELECT * FROM subject"
    res = selectall(qry)
    return jsonify(res)


@app.route('/viewstudenttochat',methods=['post'])
def viewstudenttochat():

    qry="SELECT * FROM `student` JOIN `login`ON `login`.`login id`=`student`.`login_id` WHERE `login`.`type`='student'"

    res = selectall(qry)
    return jsonify(res)
@app.route('/in_message2',methods=['post'])
def in_message():
    print(request.form)
    fromid = request.form['fid']
    print("fromid",fromid)

    toid = request.form['toid']
    print("toid",toid)

    message=request.form['msg']
    print("msg",message)
    qry = "INSERT INTO `chat` VALUES(NULL,%s,%s,%s,CURDATE())"
    value = (fromid, toid, message)
    print("pppppppppppppppppp")
    print(value)
    iud(qry, value)
    return jsonify(status='send')

@app.route('/view_message2',methods=['post'])
def view_message2():
    print("wwwwwwwwwwwwwwww")
    print(request.form)
    fromid=request.form['fid']
    print(fromid)
    toid=request.form['toid']
    print(toid)
    lmid = request.form['lastmsgid']
    print("msgggggggggggggggggggggg"+lmid)
    sen_res = []
    # qry="SELECT * FROM chat WHERE (fromid=%s AND toid=%s) OR (fromid=%s AND toid=%s) ORDER BY DATE ASC"
    qry="SELECT `from_id`,`message`,`date`,`chat_id` FROM `chat` WHERE `chat_id`>%s AND ((`to_id`=%s AND  `from_id`=%s) OR (`to_id`=%s AND `from_id`=%s)  )  ORDER BY chat_id ASC"
    print("SELECT `fromi_d`,`message`,`date`,`chat_id` FROM `chat` WHERE `chat_id`>%s AND ((`to_id`=%s AND  `from_id`=%s) OR (`to_id`=%s AND `from_id`=%s)  )  ORDER BY chat_id ASC")
    val=(str(lmid),str(toid),str(fromid),str(fromid),str(toid))
    print("fffffffffffff",val)
    res = selectall2(qry,val)
    print("resullllllllllll")
    print(res)
    if res is not None:
        return jsonify(status='ok', res1=res)
    else:
        return jsonify(status='not found')



@app.route('/get_question', methods=['post'])
def get_question():
    print (request.form)
    vid = request.form['pid']
    print(vid)

    qry = "SELECT * FROM questions where exam_id = %s"
    s = selectall2(qry,vid)
    print(s)
    return jsonify(s)

@app.route('/viewstusub', methods=['post'])
def viewstusub():
    print (request.form)
    lid = request.form['lid']


    qry = "SELECT * FROM `subject` WHERE `semester` IN(SELECT `smester` FROM `student` WHERE `login_id`=%s)"
    s = selectall2(qry,lid)
    print(s)
    return jsonify(s)


@app.route('/answertest', methods=['post'])
def answertest():
    qid=request.form['qid']
    lid = request.form['lid']
    ans = request.form['ans']
    res = request.form['res']
    qry="INSERT INTO `result`VALUES (NULL,%s,%s,%s,%s,CURDATE())"
    val=(qid,lid,ans,res)
    iud(qry,val)
    return jsonify({'task': 'success'})


"========================feedback=========================="
@app.route('/get_questionfeedback', methods=['post'])
def get_questionfeedback():
    print (request.form)
    vid = request.form['pid']
    print(vid)

    qry = "SELECT * FROM feedback where staff_id = %s"
    s = selectall2(qry,vid)
    print(s)
    return jsonify(s)


@app.route('/answertestfeddback', methods=['post'])
def answertestfeedback():
    qid=request.form['qid']
    lid = request.form['lid']
    ans = request.form['ans']
    res = request.form['res']
    q="SELECT * FROM `feed_response` WHERE `feed_id`=%s AND `stud_id`=%s"
    res=selectone(q,(qid,lid))
    if res is None:
        qry="INSERT INTO `feed_response` VALUES (NULL,%s,%s,%s,%s)"
        val=(qid,lid,ans,res)
        iud(qry,val)
        return jsonify({'task': 'success'})
    else:
        return jsonify({'task': 'invalid'})





'============================='
"=========================survey============================================================"

@app.route('/get_questionsurvey', methods=['post'])
def get_questionsurvey():

    qry = "SELECT * FROM survey"
    s = selectall(qry)

    return jsonify(s)


@app.route('/answertestsurvey', methods=['post'])
def answertestsuvey():
    qid=request.form['qid']
    lid = request.form['lid']
    ans = request.form['ans']
    res = request.form['res']
    qr = "SELECT * FROM `survey_response` WHERE `sur_id`=%s AND `stud_id`=%s"
    res = selectone(qr, (qid,lid))
    if res is None:

        qry="INSERT INTO `survey_response`VALUES (NULL,%s,%s,%s,%s)"
        val=(qid,lid,res,ans)
        iud(qry,val)
        return jsonify({'task': 'success'})
    else:
        return jsonify({'task': 'invalid'})
"======================================================================="

@app.route('/editimage', methods=['post'])
def editimage():
    pid = request.form['lid']
    image = request.files['image']
    p = secure_filename(image.filename)
    image.save(os.path.join('static/photos', p))
    q="UPDATE `student` SET `photo`=%s WHERE `login_id`=%s"
    v=(p,pid)
    iud(q,v)
    return jsonify({'task':'success'})


@app.route('/editstdprofile', methods=['post'])
def editstdprofile():
    name = request.form['name']
    ano = request.form['ano']
    sem = request.form['sem']
    gender = request.form['gender']
    dob = request.form['dob']
    add = request.form['add']
    phone = request.form['ph']
    doj = request.form['doj']
    lid = request.form['lid']


    q="UPDATE `student` SET `name`=%s,`addmission no`=%s,`smester`=%s,`gender`=%s,`dob`=%s,`address`=%s,`phone`=%s,`jointdate`=%s WHERE `login_id`=%s"
    v=(name,ano,sem,gender,dob,add,phone,doj,lid)
    iud(q,v)
    return jsonify({'task':'success'})


"=========================updateprofile teacher================="
@app.route('/editteacherimage1', methods=['post'])
def editteacherimage1():
    pid = request.form['lid']
    image = request.files['image']
    p = secure_filename(image.filename)
    image.save(os.path.join('static/photos', p))
    q="UPDATE `teacher` SET `photo`=%s WHERE `login_id`=%s"
    v=(p,pid)
    iud(q,v)
    return jsonify({'task':'success'})


@app.route('/editteacherprofile', methods=['post'])
def editteacherprofile():
    name = request.form['name']
    ano = request.form['quali']
    sem = request.form['des']
    gender = request.form['gen']
    dob = request.form['age']
    phone = request.form['ph']
    lid = request.form['lid']

    q="UPDATE `teacher` SET `name`=%s,`qualification`=%s,`designation`=%s,`gender`=%s,`age`=%s,`phone`=%s WHERE `login_id`=%s"
    v=(name,ano,sem,gender,dob,phone,lid)
    iud(q,v)
    return jsonify({'task':'success'})






@app.route('/uploadassigment', methods=['post'])
def uploadassigment():
    pid = request.form['aid']
    lid = request.form['lid']
    image = request.files['image']
    p = secure_filename(image.filename)
    image.save(os.path.join('static/photos', p))
    q="INSERT INTO `up_assignment` VALUES(NULL,%s,%s,%s,CURDATE(),'pending')"
    v=(pid,lid,p)
    iud(q,v)
    return jsonify({'task':'success'})



@app.route('/addsurvey', methods=['post'])
def addsurvey():
    print(request.form)
    name = request.form['question']
    ano = request.form['op1']
    sem = request.form['op2']
    gender = request.form['op3']
    dob = request.form['op4']
    phone = request.form['ans']
    sid = request.form['sid']


    q="INSERT INTO `survey` VALUES(NULL,%s,%s,%s,%s,%s,'kkk',CURDATE(),%s,%s)"
    v=(name,ano,sem,gender,dob,sid,phone)
    iud(q,v)
    return jsonify({'task':'valid'})

@app.route('/addfeedback', methods=['post'])
def addfeedback():
    name = request.form['question']
    ano = request.form['op1']
    sem = request.form['op2']
    gender = request.form['op3']
    dob = request.form['op4']
    ldate = request.form['ldate']

    lid = request.form['lid']

    q="INSERT INTO `feedback` VALUES(NULL,%s,%s,%s,%s,%s,%s,'hhhh',CURDATE(),%s)"
    v=(name,lid,ano,sem,gender,dob,ldate)
    iud(q,v)
    return jsonify({'task':'valid'})

@app.route('/addquestion', methods=['post'])
def addquestion():
    name = request.form['question']
    ano = request.form['op1']
    sem = request.form['op2']
    gender = request.form['op3']
    dob = request.form['op4']
    phone = request.form['ans']
    lid = request.form['eid']


    q="INSERT INTO `questions` VALUES(NULL,%s,%s,%s,%s,%s,%s,%s)"
    v=(lid,name,ano,sem,gender,dob,phone)
    iud(q,v)
    return jsonify({'task':'valid'})
@app.route('/addstudym', methods=['post'])
def addstudym():
    print(request.form)
    pid = request.form['sid']
    date = request.form['date']
    topic = request.form['topic']
    image = request.files['image']
    p = secure_filename(image.filename)
    image.save(os.path.join('static/photos', p))
    q="INSERT INTO `materials` VALUES (NULL,%s,%s,%s,%s)"
    v=(pid,topic,p,date)
    iud(q,v)
    return jsonify({'task':'success'})





@app.route('/addactualplan',methods=['post','get'])
def addactualplan():
    lid = request.args.get('id')
    qr="SELECT * FROM `subject` JOIN `assignsub`ON `subject`.`subj_id`=`assignsub`.`sub_id` WHERE `t_id`=%s"
    v1 = selectall2(qr, lid)
    q="SELECT * FROM `proposedplan` "
    v=selectall(q)
    return render_template("plan/addactualplan.html",val1=v,val=v1)



@app.route('/addactualplan1',methods=['post','get'])
def addactualplan1():

     Description  =request.form['textarea2']
     Date  =request.form['textfield']
     Hour  =request.form['select2']
     module=request.form['select3']
     status=request.form['select4']
     pid=request.form['textfield5']

     qry="INSERT INTO `actualplan` VALUES(NULL,%s,%s,%s,%s,%s,%s)"
     val=(pid,Date,Hour,module,status,Description)
     iud(qry,val)
     return redirect('addactualplan')






@app.route('/addproposedplan',methods=['post','get'])
def addproposedplan():
    lid=request.args.get('id')
    q="SELECT * FROM `subject` JOIN `assignsub`ON `subject`.`subj_id`=`assignsub`.`sub_id` WHERE `t_id`=%s"
    v=selectall2(q,lid)
    return render_template("plan/addproposedplan.html",val=v)

@app.route('/addproposedplan1',methods=['post','get'])
def addproposedplan1():
     Subject =request.form['select']
     topic =request.form['textfield2']
     Description  =request.form['textarea2']
     Date  =request.form['textfield']
     Hour  =request.form['select2']
     module=request.form['select3']

     qry="INSERT INTO `proposedplan` VALUES(NULL,%s,%s,%s,%s,%s,%s)"
     val=(topic,Description,Date,Hour,module,Subject)
     iud(qry,val)
     return '''<script>alert("added");window.location='addproposedplan'</script>'''


@app.route('/viewuploadedfiles',methods=['POST'])
def viewuploadedfiles():
    aid=request.form['aaid']
    qry="SELECT `up_assignment`.*,`student`.`name`,`addmission no` FROM `up_assignment` JOIN `student` ON `student`.`login_id`=`up_assignment`.`stud_id` WHERE `up_assignment`.`ass_id`=%s "
    res= selectall2(qry,aid)
    print(res,"hhhhhhhh")
    return jsonify(res)



@app.route('/viewqn',methods=['POST'])
def viewqn():
    eid=request.form['eeid']
    qry=" SELECT * FROM `questions` WHERE `exam_id`=%s "
    res= selectall2(qry,eid)
    return jsonify(res)


@app.route('/viewsuuu',methods=['POST'])
def viewsuuu():
    lid = request.form['sid']
    qry=" SELECT * FROM `survey`  WHERE `subid`=%s "
    res= selectall2(qry,lid)
    print(res)
    return jsonify(res)

@app.route('/viewfeedback',methods=['POST'])
def viewfeedback():
    lid=request.form['lid']
    qry=" SELECT * FROM `feedback` where staff_id=%s "
    res= selectall2(qry,lid)
    return jsonify(res)


@app.route('/viewactualplan',methods=['post','get'])
def viewactualplan():
    return render_template("plan/viewactualplan.html")
@app.route('/viewproposedplan',methods=['post','get'])
def viewproposedplan():
    q="select * from subject"
    v=selectall(q)
    return render_template("plan/viewproposedplan.html",val=v)

@app.route('/viewproposedplan1',methods=['post','get'])
def viewproposedplan1():

    sem=request.form['select']
    qry="SELECT * FROM `proposedplan`  JOIN  `subject` ON `subject`.`subj_id`=`proposedplan`.`subid` WHERE   `proposedplan`.`subid`=%s "
    t=(sem)
    val = selectall2(qry,t)
    q = "select * from subject"
    v = selectall(q)
    return render_template("plan/viewproposedplan.html",val1=val,val=v)

app.run(host='0.0.0.0',port='5000')