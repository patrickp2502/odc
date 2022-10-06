import React from 'react'
import { Grid, Paper } from '@mui/material'

type MainTemplateProps = {
    children: React.ReactNode,
    header: React.ReactNode
}

const PageTemplate: React.FC<MainTemplateProps> = (props) => {
    return (
        <Grid container
            margin={'auto'}
            justifyItems={'center'}>
            <Grid
                item xs={100}
                minHeight={'50px'}
                bgcolor={'red'}
                alignContent='center'>
                {props.header}
            </Grid>
            <Grid item
                xs={12}
                minHeight={'600px'}
                padding={'2em 2em'}
                alignContent='center'>
                <Paper elevation={2}>
                    {props.children}
                </Paper>
            </Grid>
        </Grid>
    )
}

export default PageTemplate