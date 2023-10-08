// import React from 'react'
import './Register.css'
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { UserRegisterDto, signUpSchema } from '../../lib/types';

function Register() {

  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting, isValid },
    reset,
  } = useForm<UserRegisterDto>({
    resolver: zodResolver(signUpSchema),
  });

  const onRegisterSubmit = async (data: UserRegisterDto) => {
    console.log(data)

    reset();
  }

  console.log("Validation is " + isValid)

  return (
    <div className="page-wrapper bg-gra-03 p-t-45 p-b-50">
      <div className="wrapper wrapper--w790">
        <div className="card card-5">
          <div className="card-heading">
            <h2 className="title">Registration Form</h2>
          </div>
          <div className="card-body">

            <form onSubmit={handleSubmit(onRegisterSubmit)}>
              {/* FIRST NAME AND LAST NAME */}
              <div className="form-row m-b-55">
                <div className="name">Name</div>
                <div className="value">
                  <div className="row row-space">
                    {/* First Name */}
                    <div className="col-2">
                      <div className="input-group-desc">
                        <input
                          {...register("firstName")}
                          className="input--style-5"
                          type="text"
                          placeholder='First name'
                          name="firstName" />
                        {errors.firstName && (
                          <i><p style={{ color: "red" }}>{`${errors.firstName.message}`}</p></i>
                        )}
                      </div>
                    </div>
                    {/* Last Name */}
                    <div className="col-2">
                      <div className="input-group-desc">
                        <input
                          {...register("lastName")}
                          className="input--style-5"
                          type="text"
                          placeholder='Last name'
                          name="lastName" />
                        {errors.lastName && (
                          <i><p style={{ color: "red" }}>{`${errors.lastName.message}`}</p></i>
                        )}
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              {/* EMAIL */}
              <div className="form-row">
                <div className="name">Email</div>
                <div className="value">
                  <div className="input-group">
                    <input
                      {...register("email")}
                      className="input--style-5"
                      type="email"
                      placeholder='example@gmail.com'
                      name="email" />
                    {errors.email && (
                      <i><p style={{ color: "red" }}>{`${errors.email.message}`}</p></i>
                    )}
                  </div>
                </div>
              </div>

              {/* PASSWORD AND CONFIRM PASSWORD*/}
              <div className="form-row m-b-55">
                <div className="name">Password</div>
                <div className="value">
                  <div className="row row-space">
                    <div className="col-2">
                      <div className="input-group-desc">
                        <input
                          {...register("password")}
                          className="input--style-5"
                          type="password"
                          placeholder='Password'
                          name="password" />
                        {errors.password && (
                          <i><p style={{ color: "red" }}>{`${errors.password.message}`}</p></i>
                        )}
                      </div>
                    </div>
                    <div className="col-2">
                      <div className="input-group-desc">
                        <input
                          {...register("confirmPassword")}
                          className="input--style-5"
                          type="password"
                          placeholder='Confirm password'
                          name="confirmPassword" />
                        {errors.confirmPassword && (
                          <i><p style={{ color: "red" }}>{`${errors.confirmPassword.message}`}</p></i>
                        )}
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              {/* PHONE NUMBER */}
              <div className="form-row m-b-55">
                <div className="name">Phone number</div>
                <div className="value">
                  <div className="row row-refine">
                    <div className="col-12">
                      <div className="input-group-desc">
                        <input
                          {...register("phoneNumber")}
                          className="input--style-5"
                          type="text"
                          placeholder='0877123456'
                          name="phoneNumber" />
                        {errors.phoneNumber && (
                          <i><p style={{ color: "red" }}>{`${errors.phoneNumber.message}`}</p></i>
                        )}
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              {/* COUNTRY AND CITY */}
              <div className="form-row m-b-55">
                <div className="name">Address</div>
                <div className="value">
                  <div className="row row-space">
                    {/* COUNTRY */}
                    <div className="col-5">
                      <div className="input-group">
                        <div className="rs-select2 js-select-simple select--no-search">
                          <select
                            className="form-select"
                            id="floatingSelect"
                            aria-label="Floating label select example"
                            name="country"
                          // onChange={{ ...register("country") }}
                          >
                            <option
                            // {...register("country")}
                            >BULGARIA</option>
                            <option
                            // {...register("country")}
                            >GERMANY</option>
                          </select>
                          <label
                            className="label--desc">Country</label>
                        </div>
                      </div>
                    </div>

                    {/* CITY */}
                    <div className="col-6">
                      <div className="input-group-desc">
                        <input
                          {...register("city")}
                          className="input--style-5"
                          type="text"
                          placeholder='City'
                          name="city" />
                        {errors.city && (
                          <i><p style={{ color: "red" }}>{`${errors.city.message}`}</p></i>
                        )}
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              {/* STREET AND NUMBER */}
              <div className="form-row m-b-55">
                <div className="name"></div>
                <div className="value">
                  <div className="row row-space">
                    <div className="col-9">
                      <div className="input-group-desc">
                        <input
                          {...register("street")}
                          className="input--style-5"
                          type="text"
                          placeholder='Street'
                          name="street" />
                      </div>
                    </div>
                    <div className="col-3">
                      <div className="input-group-desc">
                        <input
                          {...register("streetNumber")}
                          className="input--style-5"
                          type="text"
                          placeholder='Number'
                          name="streetNumber" />
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              {/* TYPE OF CUSTOMER */}
              {/* <div className="form-row p-t-20">
                <label className="label label--block">Type of customer?</label>
                <div className="p-t-15">
                  <label className="radio-container m-r-55">PRIVATE
                    <input
                      // {...register("type")}
                      type="radio"
                      // checked="checked" 
                      name="exist" />
                    <span className="checkmark"></span>
                  </label>
                  <label className="radio-container">BUSINESS
                    <input
                      // {...register("type")} 
                      type="radio" name="exist" />
                    <span className="checkmark"></span>
                  </label>
                </div>
              </div> */}

              {/* SUBMIT BUTTON */}
              <div>
                <button
                  disabled={isSubmitting}
                  onClick={() => console.log("Clicked!")}
                  className="btn btn--radius-2 btn--red"
                  type="submit">
                  Register
                </button>
              </div>

              {/* REDIRECT TO LOGIN PAGE */}
              <div style={{ marginTop: '20px' }}>Have already an account? <a href="/login">Login here</a> </div>
            </form>
          </div>
        </div>
      </div >
    </div >
  )
}

export default Register;