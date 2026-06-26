import os
from flask import Flask
from routes.web_routes import web_bp
from routes.api_routes import api_bp

app = Flask(__name__)
# In a real production application, this should be an environment variable
app.secret_key = os.environ.get('SECRET_KEY', 'default_secure_secret_key_change_me')

# Register blueprints
app.register_blueprint(web_bp)
app.register_blueprint(api_bp, url_prefix='/api')


@app.after_request
def add_header(response):
    response.headers['Cache-Control'] = 'no-store, no-cache, must-revalidate, max-age=0'
    response.headers['Pragma'] = 'no-cache'
    response.headers['Expires'] = '-1'
    return response

if __name__ == '__main__':

    # Do not use debug=True in production
    app.run(host='0.0.0.0', port=5000, debug=True)
