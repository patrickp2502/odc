import React from 'react'
import { Card, Container, Grid, Paper } from '@mui/material'

type MainTemplateProps = {
    children: React.ReactNode,
    header: React.ReactNode
}

const OperationPageTemplate: React.FC<MainTemplateProps> = (props) => {
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
                maxHeight={'500px'}
                padding={'2em 2em'}
                alignContent='center'>
                <Paper elevation={3}>
                    {props.children}
                </Paper>
            </Grid>
        </Grid>
    )
}

export default OperationPageTemplate