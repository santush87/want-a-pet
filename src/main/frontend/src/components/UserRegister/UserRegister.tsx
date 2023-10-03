import React from 'react'
import {
    MDBBtn,
    MDBContainer,
    MDBCard,
    MDBCardBody,
    MDBInput,
    MDBCheckbox,
    MDBCol,
    MDBRow,
    MDBRadio,
    // MDBSelect,
} from 'mdb-react-ui-kit'

import Select, { GroupBase, OptionsOrGroups } from 'react-select'

import { useForm } from 'react-hook-form'
import { UserRegisterDto } from '../../lib/types';

function UserRegister() {
    const {
        register,
        handleSubmit,
        formState: { errors, isSubmitting },
        reset,
        getValues,
    } = useForm();

    const country: OptionsOrGroups<{
        value: string;
        label: string;
    }, GroupBase<{
        value: string;
        label: string;
    }>> | undefined = [
            { value: 'BULGARIA', label: 'Bulgaria' },
            { value: 'ROMANIA', label: 'Romania' },
            { value: 'GREECE', label: 'Greece' },
            { value: 'GERMANY', label: 'Germany' },
            { value: 'SPAIN', label: 'Spain' },
            { value: 'SWITZERLAND', label: 'Switzerland' },
        ]

    return (
        <MDBContainer fluid className='d-flex align-items-center justify-content-center bg-image' style={{ backgroundImage: 'url(https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp)' }}>
            <div className='mask gradient-custom-3'></div>
            <MDBCard className='m-5' style={{ maxWidth: '600px' }}>
                <MDBCardBody className='px-5'>
                    <h2 className="text-uppercase text-center mb-5">Create an account</h2>

                    {/* FIRST AND LAST NAME */}
                    <MDBRow>
                        <MDBCol md='6'>
                            <MDBInput
                                wrapperClass='mb-4'
                                label='First Name'
                                size='lg'
                                id='form1'
                                type='text' />
                        </MDBCol>
                        <MDBCol md='6'>
                            <MDBInput
                                wrapperClass='mb-4'
                                label='Last Name'
                                size='lg'
                                id='form1'
                                type='text' />
                        </MDBCol>
                    </MDBRow>

                    {/* EMAIL  */}
                    <MDBInput wrapperClass='mb-4'
                        label='Your Email'
                        size='lg'
                        id='form2'
                        type='email' />

                    {/* PASSWORD AND CONFIRM PASSWORD */}
                    <MDBRow>
                        <MDBCol><MDBInput
                            wrapperClass='mb-4'
                            label='Password'
                            size='lg'
                            id='form3' type='password' /></MDBCol>
                        <MDBCol><MDBInput
                            wrapperClass='mb-4'
                            label='Repeat your password'
                            size='lg'
                            id='form3' type='password' /></MDBCol>
                    </MDBRow>

                    {/* TYPE OF USER */}
                    <MDBRow>
                        <MDBInput wrapperClass='mb-4' label='Birthday' size='lg' id='form3' type='text' />

                        <div className='d-md-flex ustify-content-start align-items-center mb-4'>
                            <h6 className="fw-bold mb-0 me-4">Type of user: </h6>
                            <MDBRadio name='inlineRadio' id='inlineRadio1' value='option1' label='Private' inline />
                            <MDBRadio name='inlineRadio' id='inlineRadio2' value='option2' label='Business' inline />
                        </div>

                    </MDBRow>

                    {/* COUNTRY AND CITY */}
                    <MDBRow>
                        <MDBCol md='6' className='mb-4'>
                            <Select options={country} />
                            <p>Country</p>
                        </MDBCol>
                        <MDBCol md='6'>
                            <MDBInput
                                wrapperClass='mb-4'
                                label='City'
                                size='lg'
                                id='form1'
                                type='text' />
                        </MDBCol>
                    </MDBRow>

                    {/* STREET AND NUMBER */}
                    <MDBRow>
                        <MDBCol md='10'>
                            <MDBInput
                                wrapperClass='mb-4'
                                label='Street'
                                size='lg'
                                id='form1'
                                type='text' />
                        </MDBCol>
                        <MDBCol md='2'>
                            <MDBInput
                                wrapperClass='mb-4'
                                label='Number'
                                size='lg'
                                id='form1'
                                type='text' />
                        </MDBCol>
                    </MDBRow>


                    <div className='d-flex flex-row justify-content-center mb-4'>
                        <MDBCheckbox
                            name='flexCheck'
                            id='flexCheckDefault'
                            label='I agree all statements in Terms of service' />
                    </div>
                    <MDBBtn
                        className='mb-4 w-100 gradient-custom-4'
                        size='lg'>Register</MDBBtn>
                </MDBCardBody>
            </MDBCard>
        </MDBContainer>
    )
}

export default UserRegister