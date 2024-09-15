import React, { useEffect, useState } from 'react';
import { createEmployee, getEmployee, updateEmployee } from '../services/EmployeeService';
import { useNavigate, useParams } from 'react-router-dom';

export const EmployeeComponent = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const { id } = useParams();
  const [email, setEmail] = useState('');
  const [errors, setErrors] = useState({
    firstName: '',
    lastName: '',
    email: ''
  });

  const navigator = useNavigate('');

  useEffect(() => {
    if (id) {
      getEmployee(id)
        .then((response) => {
          setFirstName(response.data.firstName);
          setLastName(response.data.lastName); // Fixed typo here
          setEmail(response.data.email);
        })
        .catch(error => {
          console.error(error);
        });
    }
  }, [id]); // Added missing dependency

  const saveOrUpdateEmployee = (e) => {
    e.preventDefault();
    if (validationForm()) {
      const employee={firstName,lastName,email};
      console.log(employee);

      if(id)
      {
         updateEmployee(id,employee).then((response)=>{
          console.log(response.data);
          navigator('/employees');
         }).catch(error=>{
          console.error(error);
         })
      }
      else{
        createEmployee(employee)
        .then((response) => {
          console.log(response.data);
          navigator('/employees');
        }).catch(error=>{

        })
      }
      
    }
  };

  function validationForm() {
    let valid = true;
    const errorsCopy = { ...errors };
    if (firstName.trim()) {
      errorsCopy.firstName = '';
    } else {
      errorsCopy.firstName = 'First name is required';
      valid = false;
    }

    if (lastName.trim()) {
      errorsCopy.lastName = '';
    } else {
      errorsCopy.lastName = 'Last name is required';
      valid = false;
    }

    if (email.trim()) {
      errorsCopy.email = '';
    } else {
      errorsCopy.email = 'Email name is required';
      valid = false;
    }
    setErrors(errorsCopy);
    return valid;
  }

  function pageTitle() {
    if (id) {
      return <h2 className='text-center'>Update Employee</h2>;
    } else {
      return <h2 className='text-center'>Add Employee</h2>;
    }
  }

  return (
    <div className='container mt-5'>
      <div className='row'>
        <div className='card col-md-6 offset-md-3'>
          {pageTitle()}
          <div className='card-body'>
            <form>
              <div className='form-group mb-2'>
                <label className='form-level'>First Name</label>
                <input
                  type='text'
                  placeholder='Enter Employee First Name'
                  name='firstName'
                  value={firstName}
                  className={`form-control ${errors.firstName ? 'is-invalid' : ''}`}
                  onChange={(event) => setFirstName(event.target.value)}
                />
                {errors.firstName && <div className='invalid-feedback'>{errors.firstName} </div>}
              </div>
              <div className='form-group mb-2'>
                <label className='form-level'>Last Name</label>
                <input
                  type='text'
                  placeholder='Enter Employee Last Name'
                  name='lastName'
                  value={lastName}
                  className={`form-control ${errors.lastName ? 'is-invalid' : ''}`}
                  onChange={(event) => setLastName(event.target.value)}
                />
                {errors.lastName && <div className='invalid-feedback'>{errors.lastName} </div>}
              </div>
              <div className='form-group mb-2'>
                <label className='form-level'>Email</label>
                <input
                  type='text'
                  placeholder='Enter Employee Email here'
                  name='email'
                  value={email}
                  className={`form-control ${errors.email ? 'is-invalid' : ''}`}
                  onChange={(event) => setEmail(event.target.value)}
                />
                {errors.email && <div className='invalid-feedback'>{errors.email} </div>}
              </div>
              <button className='btn btn-success' onClick={saveOrUpdateEmployee}>
                Submit
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};
