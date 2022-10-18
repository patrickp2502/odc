import { Dialog, DialogContent, DialogTitle, Container, TextField, Button, Grid } from '@mui/material'
import { DateTimePicker } from '@mui/x-date-pickers';
import React from 'react'
import { Controller, useForm } from 'react-hook-form'
import { yupResolver } from '@hookform/resolvers/yup'
import useAddStepFormSchema from '../../../shared/customHooks/validation/useAddStepFormSchema';
import { useMutation } from 'react-query';
import { postOperationStep } from '../../../shared/dataProvider/api';

interface AddStepDialogProps {
    open: boolean,
    defaultBatchId: string,
    onClose: any
}

type AddStepFormInputs = {
    operatorName: string,
    batchId: string,
    timeStamp: Date
}

const AddStepDialog: React.FC<AddStepDialogProps> = (props) => {

    const validationSchema = useAddStepFormSchema();

    const { handleSubmit, formState, control } = useForm<AddStepFormInputs>(
        {
            resolver: validationSchema ? yupResolver(validationSchema) : undefined,
            defaultValues: {
                timeStamp: new Date()
            }
        });

    const submitOperationStep = (data: AddStepFormInputs) => {
        console.log(data);
    }


    return (
        <Dialog
            onClose={props.onClose}
            open={props.open}>
            <DialogTitle>Add Step</DialogTitle>
            <DialogContent>Enter Valid Data</DialogContent>
            <Container sx={{ padding: "10px" }}>
                <form onSubmit={handleSubmit(submitOperationStep)}>
                    <Grid container
                        display={"flex"}
                        flexDirection={"column"}
                        spacing={2}>
                        <Grid item>
                            <Controller
                                control={control}
                                name='operatorName'
                                render={({ field }) =>
                                    <TextField
                                        {...field}
                                        label='Operator'
                                        error={!!formState.errors.operatorName}
                                        helperText={formState.errors.operatorName?.message}
                                        defaultValue={""}
                                    />

                                }
                            />
                        </Grid>
                        <Grid item>
                            <Controller
                                control={control}
                                name='batchId'
                                render={({ field }) =>
                                    <TextField
                                        {...field}
                                        label="BatchNr"
                                        defaultValue={props.defaultBatchId}
                                        error={!!formState.errors.batchId}
                                        helperText={formState.errors.batchId?.message}
                                    />
                                } />
                        </Grid>
                        <Grid item>
                            palceholder
                        </Grid>
                        <Grid item>
                            <Button
                                variant='contained'
                                type='submit'>
                                Add
                            </Button>
                        </Grid>
                    </Grid>
                </form>
            </Container >
        </Dialog >
    )
}

export default AddStepDialog