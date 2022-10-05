import { Grid } from '@mui/material'
import React from 'react'

type HeaderProps = {
    navbar: React.ReactNode,
    logo: React.ReactNode
}

const Header: React.FC<HeaderProps> = (props) => {
    return (
        <Grid container
            flexDirection={'column'}
            height={'100%'}
            alignItems={'end'}>
            <Grid item alignSelf={'start'}>
                {props.logo}
            </Grid>
            <Grid item
                alignSelf={'center'}>
                {props.navbar}
            </Grid>
        </Grid>
    )
}

export default Header