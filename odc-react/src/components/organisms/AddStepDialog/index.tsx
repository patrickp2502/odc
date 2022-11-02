import { Dialog, DialogContent, DialogTitle, Container, TextField, Button, Grid } from '@mui/material'
import React, { useEffect } from 'react'
import { Controller, useForm } from 'react-hook-form'
import { yupResolver } from '@hookform/resolvers/yup'
import useAddStepFormSchema from '../../../shared/customHooks/validation/useAddStepFormSchema';
import { AddStepInformation } from '../../../shared/types/types';
import moment from 'moment'
interface AddStepDialogProps {
    open: boolean,
    defaultBatchId: string,
    onClose: any,
    onSubmit: (data: AddStepInformation) => void

}

const AddStepDialog: React.FC<AddStepDialogProps> = (props) => {

    const validationSchema = useAddStepFormSchema();
    const { handleSubmit, formState, control, reset } = useForm<AddStepInformation>(
        {
            resolver: validationSchema ? yupResolver(validationSchema) : undefined,
            defaultValues: {
                timeStamp: moment().format(),
                operatorName: "",
                batchId: props.defaultBatchId ? props.defaultBatchId : ""
            }
        });

    const timeStamp = () => moment().format().slice(0, 19);

    const submitOperationStep = (data: AddStepInformation) => {
        data.timeStamp = timeStamp();
        console.log("transmitted timestamp", data.timeStamp)
        props.onSubmit(data)
        reset();
    }

    const handleOnClose = () => {
        reset();
        props.onClose();
    }

    return (
        <Dialog
            onClose={handleOnClose}
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