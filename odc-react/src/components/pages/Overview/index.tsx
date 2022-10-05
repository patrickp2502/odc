import { Card } from '@mui/material'
import React from 'react'
import ResponsiveAppBar from '../../organisms/ResponsiveAppBar'
import PageTemplate from '../../templates/PageTemplate'

const Overview = () => {
    return (
        <PageTemplate header={<ResponsiveAppBar />}>
            <Card>here comes the OVERVIEW</Card>
        </PageTemplate>
    )
}

export default Overview