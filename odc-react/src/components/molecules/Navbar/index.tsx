import { styled, Button, ButtonGroup, Container } from '@mui/material'

import React from 'react'



const Navbar: React.FC = () => {
    return (
        <Container >
            <ButtonGroup>
                <Button variant={'contained'}>Operations</Button>
                <Button variant={'contained'}>Steps</Button>
                <Button variant={'contained'}>Overview</Button>
            </ButtonGroup>
        </Container>
    )
}

export default Navbar