import { TextField, TextFieldProps } from '@mui/material'
import React from 'react'
import { Control, Controller, useForm } from 'react-hook-form'

interface FormTextFieldProps {
  name: string,
  control: Control,
  label?: string
}

const FormTextField: React.FC<FormTextFieldProps> = (props) => {

  return (
    // <Controller
    //   name={props.name}
    //   control={props.control}
    //   render={(
    //     { field: { onChange, value } }) =>
    //     <TextField onChange={onChange} value={value} />}>


    // </Controller>
    <></>
  )
}


export default FormTextField