import React from 'react'
import { Grid } from '@mui/material'
import { color } from '@mui/system'
import ResponsiveAppBar from '../../organisms/ResponsiveAppBar'

type MainProps = {
    children: React.ReactNode,
}

const Main: React.FC<MainProps> = (props) => {
    return (
        <Grid container
            margin={'auto'}
            justifyItems={'center'}>
            <Grid
                item xs={100}
                minHeight={'50px'}
                bgcolor={'red'}
                alignContent='center'>
                <ResponsiveAppBar />
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

export default Main