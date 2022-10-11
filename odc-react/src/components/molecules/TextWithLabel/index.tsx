import React from 'react'
import { Grid, Typography } from '@mui/material'


interface TextWithLabelProps {
    label: string,
    children: string
}



const TextWithLabel: React.FC<TextWithLabelProps> = (props) => {
    return (
        <Grid container
            display={'flex'}
            direction={'column'}
            spacing={1}>
            <Grid item>
                <Typography
                    fontWeight={'700'}>
                    {props.label}
                </Typography>
            </Grid>
            <Grid item>
                <Typography>
                    {props.children}
                </Typography>
            </Grid>
        </Grid>
    )
}

export default TextWithLabel