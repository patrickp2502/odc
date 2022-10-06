import { Grid } from '@mui/material'
import React from 'react'

interface OperationDetailsBodyTemplateProps {
    detailContainer: React.ReactNode,
    stepsContainer: React.ReactNode
}

const OperationDetailsBodyTemplate: React.FC<OperationDetailsBodyTemplateProps> = (props) => {
    return (
        <Grid container
            display={'flex'}
        >
            <Grid item xs={12}>
                {props.detailContainer}
            </Grid>
            <Grid item xs={12}>
                {props.stepsContainer}
            </Grid>
        </Grid>


    )
}

export default OperationDetailsBodyTemplate