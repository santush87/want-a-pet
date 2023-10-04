import React from 'react'
import './Login.css'

function Login() {
    return (
        <div className="page-wrapper bg-gra-03 p-t-45 p-b-50">
            <div className="wrapper wrapper--w790">
                <div className="card card-5">
                    <div className="card-heading">
                        <h2 className="title">Login</h2>
                    </div>
                    <div className="card-body">

                        <form method="POST">


                            <div className="form-row">
                                <div className="name">Email</div>
                                <div className="value">
                                    <div className="input-group">
                                        <input className="input--style-5" type="email" name="email" />
                                    </div>
                                </div>
                            </div>
                            <div className="form-row">
                                <div className="name">Password</div>
                                <div className="value">
                                    <div className="input-group">
                                        <input className="input--style-5" type="password" name="password" />
                                    </div>
                                </div>
                            </div>


                            <div>
                                <button className="btn btn--radius-2 btn--red" type="submit">Login</button>
                            </div>
                            <div style={{ marginTop: '20px' }}>Does not have an Account? <a href="/register">Register here</a> </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Login