import React from 'react'
import './Register.css'
// import { GroupBase, OptionsOrGroups } from 'react-select';
// import { Address } from "../../lib/types"
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { SignUpSchema, signUpSchema } from '../../lib/types';

function Register() {



    const {
        register,
        handleSubmit,
        formState: { errors, isSubmitting },
        reset,
    } = useForm<SignUpSchema>({
        resolver: zodResolver(signUpSchema),
    });

    // const country: OptionsOrGroups<{
    //     value: string;
    //     label: string;
    // }, GroupBase<{
    //     value: string;
    //     label: string;
    // }>> | undefined = [
    //         { value: 'BULGARIA', label: 'Bulgaria' },
    //         { value: 'ROMANIA', label: 'Romania' },
    //         { value: 'GREECE', label: 'Greece' },
    //         { value: 'GERMANY', label: 'Germany' },
    //         { value: 'SPAIN', label: 'Spain' },
    //         { value: 'SWITZERLAND', label: 'Switzerland' },
    //     ]

    const onRegisterSubmit = async (data: SignUpSchema) => {


        reset();
    }

    return (
        <div className="page-wrapper bg-gra-03 p-t-45 p-b-50">
            <div className="wrapper wrapper--w790">
                <div className="card card-5">
                    <div className="card-heading">
                        <h2 className="title">Registration Form</h2>
                    </div>
                    <div className="card-body">

                        <form onSubmit={handleSubmit(onRegisterSubmit)}
                            method="POST">
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
                                                {/* <label
                                                    className="label--desc">first name</label> */}
                                                {errors.firstName && (
                                                    <p className='text-red-500'>{`${errors.firstName.message}`}</p>
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
                                                {/* <label
                                                    className="label--desc">last name</label> */}
                                                {errors.lastName && (
                                                    <p className='text-red-500'>{`${errors.lastName.message}`}</p>
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
                                            <p className='text-red-500'>{`${errors.email.message}`}</p>
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
                                                {/* <label
                                                    className="label--desc">Password</label> */}
                                                {errors.password && (
                                                    <p className='text-red-500'>{`${errors.password.message}`}</p>
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
                                                {/* <label
                                                    className="label--desc">Confirm password</label> */}
                                                {errors.confirmPassword && (
                                                    <p className='text-red-500'>{`${errors.confirmPassword.message}`}</p>
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
                                                    placeholder='+359877123456'
                                                    name="phoneNumber" />
                                                {errors.phoneNumber && (
                                                    <p className='text-red-500'>{`${errors.phoneNumber.message}`}</p>
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
                                                        name="country">
                                                        <option>Bulgaria</option>
                                                        <option>Germany</option>
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
                                                    name="last_name" />
                                                {/* <label
                                                    className="label--desc">City</label> */}
                                                {errors.city && (
                                                    <p className='text-red-500'>{`${errors.city.message}`}</p>
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
                                                {/* <label
                                                    className="label--desc">Street</label> */}
                                            </div>
                                        </div>
                                        <div className="col-3">
                                            <div className="input-group-desc">
                                                <input
                                                    {...register("streetNumber")}
                                                    className="input--style-5"
                                                    type="text"
                                                    placeholder='Number'
                                                    name="number" />
                                                {/* <label
                                                    className="label--desc">Number</label> */}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            {/* TYPE OF CUSTOMER */}
                            <div className="form-row p-t-20">
                                <label className="label label--block">Type of customer?</label>
                                <div className="p-t-15">
                                    <label className="radio-container m-r-55">Private
                                        <input
                                            type="radio"
                                            // checked="checked" 
                                            name="exist" />
                                        <span className="checkmark"></span>
                                    </label>
                                    <label className="radio-container">Business
                                        <input type="radio" name="exist" />
                                        <span className="checkmark"></span>
                                    </label>
                                </div>
                            </div>

                            {/* SUBMIT BUTTON */}
                            <div>
                                <button
                                    // disabled={isSubmitting}
                                    // onSubmit={() => console.log("clicked") }
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