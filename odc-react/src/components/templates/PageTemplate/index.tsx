import React from 'react'
import { Grid } from '@mui/material'

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
                alignContent='center'>
                {props.children}
            </Grid>
            <Grid item xs={12} minHeight={'100px'} bgcolor={'black'}>

            </Grid>
        </Grid>
    )
}

export default PageTemplate