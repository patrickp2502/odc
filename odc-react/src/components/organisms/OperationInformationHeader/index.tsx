import React from 'react'
import { OperationData } from '../../../shared/types/interfaces'
import { Fab, Grid } from '@mui/material'
import AddIcon from '@mui/icons-material/Add'
import { convertToFromatedDateString } from '../../../shared/services/DateTimeConverter'
import TextWithLabel from '../../molecules/TextWithLabel'

interface OperationInformationHeaderProps {
    operationData: OperationData,
    onClick: React.MouseEventHandler<HTMLButtonElement>
}

const OperationInformationHeader: React.FC<OperationInformationHeaderProps> = (props) => {
    const startTime = convertToFromatedDateString(props.operationData.startTime);
    const stopTime = convertToFromatedDateString(props.operationData.stopTime);
    return (
        <Grid container
            display={'flex'}
            spacing={2}>
            <Grid item>
                <Fab onClick={props.onClick}
                    size="large"
                    sx={{
                        color: "black",
                        bgcolor: "orange"
                    }}>
                    <AddIcon />
                </Fab>
            </Grid>


            <Grid item>
                <TextWithLabel label={'Batch'}>
                    {props.operationData.batchId}
                </TextWithLabel>
            </Grid>
            <Grid item>
                <TextWithLabel label={'Start'}>
                    {startTime}
                </TextWithLabel>
            </Grid>
            <Grid item>
                <TextWithLabel label={'Stop'}>
                    {stopTime}
                </TextWithLabel>
            </Grid>
            <Grid item>
                <TextWithLabel label={'Status'}>
                    {props.operationData.status.status}
                </TextWithLabel>
            </Grid>
        </Grid>
    )
}

export default OperationInformationHeader