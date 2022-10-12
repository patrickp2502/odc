import { Dialog, DialogContent, DialogTitle, Container, TextField, Button, Grid } from '@mui/material'
import { DateTimePicker } from '@mui/x-date-pickers';
import React, { useState } from 'react'
import { useForm } from 'react-hook-form'

interface AddStepDialogProps {
    open: boolean;
}

type AddStepForm = {
    operatorName: string,
    batchId: string,
    timeStamp: Date
}



//TODO add form for adding a step
//TODO 
const AddStepDialog: React.FC<AddStepDialogProps> = (props) => {

    const [date, setDate] = useState<Date | null>(new Date());




    return (
        <Dialog open>
            <DialogTitle>Add Step</DialogTitle>
            <DialogContent>Enter Valid Data</DialogContent>
            <Container sx={{ padding: "10px" }}>
                <form>
                    <Grid container
                        display={"flex"}
                        flexDirection={"column"}
                        spacing={2}>
                        <Grid item>
                            <TextField label="Operator" />
                        </Grid>
                        <Grid item>
                            <TextField label="BatchNr" />
                        </Grid>
                        <Grid item>
                            <DateTimePicker
                                renderInput={(props) => <TextField {...props} />}
                                label="DateTimePicker"
                                value={date}
                                onChange={(newValue) => setDate(newValue)}
                            />
                        </Grid>
                        <Grid item>
                            <Button
                                variant='contained'
                                type='submit'
                                onSubmit={() => console.log(date)}>
                                Add
                            </Button>
                        </Grid>
                    </Grid>
                </form>
            </Container>

        </Dialog>
    )
}

export default AddStepDialog