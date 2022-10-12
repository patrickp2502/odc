import React from 'react'
import { OperationData } from '../../../shared/types/interfaces'
import { Grid } from '@mui/material'
import { convertToFromatedDateString } from '../../../shared/services/DateTimeConverter'
import TextWithLabel from '../../molecules/TextWithLabel'

interface OperationInformationHeaderProps {
    operationData: OperationData
}

const OperationInformationHeader: React.FC<OperationInformationHeaderProps> = (props) => {
    const startTime = convertToFromatedDateString(props.operationData.startTime);
    const stopTime = convertToFromatedDateString(props.operationData.stopTime);
    return (
        <Grid container
            display={'flex'}
            spacing={2}>
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