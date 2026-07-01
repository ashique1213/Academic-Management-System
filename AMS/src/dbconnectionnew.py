import os
import psycopg2
import psycopg2.extras
from dotenv import load_dotenv

load_dotenv()


def get_connection():
    return psycopg2.connect(
        host=os.getenv("DB_HOST", "localhost"),
        port=os.getenv("DB_PORT", 5432),
        user=os.getenv("DB_USER", "postgres"),
        password=os.getenv("DB_PASSWORD", "12345"),
        dbname=os.getenv("DB_NAME", "ams"),
    )


def iud(qry, val=None):
    id = None
    with get_connection() as con:
        with con.cursor(cursor_factory=psycopg2.extras.RealDictCursor) as cmd:
            if val is not None:
                if not isinstance(val, (list, tuple)):
                    val = (val,)
                cmd.execute(qry, val)
            else:
                num_params = qry.count("%s")
                if num_params > 0:
                    cmd.execute(qry, (None,) * num_params)
                else:
                    cmd.execute(qry)
            # If the query contains RETURNING, we try to fetch the returned row (e.g., the inserted ID)
            if "RETURNING" in qry.upper():
                id = list(cmd.fetchone().values())[0]
        con.commit()
    return id


def selectone(qry, val=None):
    with get_connection() as con:
        with con.cursor(cursor_factory=psycopg2.extras.RealDictCursor) as cmd:
            if val is not None:
                if not isinstance(val, (list, tuple)):
                    val = (val,)
                cmd.execute(qry, val)
            else:
                num_params = qry.count("%s")
                if num_params > 0:
                    cmd.execute(qry, (None,) * num_params)
                else:
                    cmd.execute(qry)
            res = cmd.fetchone()
    return res


def selectonee(qry):
    return selectone(qry)


def selectall(qry, val=None):
    with get_connection() as con:
        with con.cursor(cursor_factory=psycopg2.extras.RealDictCursor) as cmd:
            if val is not None:
                if not isinstance(val, (list, tuple)):
                    val = (val,)
                cmd.execute(qry, val)
            else:
                num_params = qry.count("%s")
                if num_params > 0:
                    cmd.execute(qry, (None,) * num_params)
                else:
                    cmd.execute(qry)
            res = cmd.fetchall()
    return res


def selectall2(qry, val):
    return selectall(qry, val)
