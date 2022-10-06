import { Grid } from '@mui/material'
import React from 'react'


interface StepBodyTemplateProps {
    children?: React.ReactNode,
    leftContainer: React.ReactNode,
    rightContainer: React.ReactNode,
    subnav: React.ReactNode

}

const StepBodyTemplate: React.FC<StepBodyTemplateProps> = (props) => {
    return (
        <Grid container minHeight={"500"}
            spacing={1}
            justifyContent={"start"}
            columns={{ xs: 1, sm: 12 }}
        >
            <Grid item
                xs={12}>
                {props.subnav}
            </Grid>
            <Grid item
                xs={6}
            >
                {props.rightContainer}
            </Grid>

            <Grid item xs={6}>
                {props.rightContainer}
            </Grid>
        </Grid >
    )
}

export default StepBodyTemplate