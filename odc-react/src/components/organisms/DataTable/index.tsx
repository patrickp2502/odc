import { Paper, styled, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material'
import { fontWeight, height } from '@mui/system'
import React from 'react'
import { DataTableProps, DataRowType } from '../../../shared/types/interfaces'



const StyledTableHeaderCell = styled(TableCell)({
    backgroundColor: "orange",
    fontWeight: "900",
    color: "white",
})

const StyledTableCell = styled(TableCell)({
    fontSize: "11px"
})


const DataTable: React.FC<DataTableProps> = (props) => {
    return (
        <TableContainer component={Paper} sx={{ maxHeight: "500px" }}>
            <Table stickyHeader>
                <TableHead>
                    {props.headerKeyTemplate.map(headerKeyPair =>
                        <StyledTableHeaderCell>{headerKeyPair.headerName}</StyledTableHeaderCell>)}
                </TableHead>
                <TableBody>
                    {props.data.map((dataRow: DataRowType) => {
                        return (
                            <TableRow>
                                {props.headerKeyTemplate.map(keyHeaderPair =>
                                    <StyledTableCell>{dataRow[keyHeaderPair.key]}</StyledTableCell>)}
                            </TableRow>)
                    })
                    }
                </TableBody>
            </Table>
        </TableContainer>
    )
}

export default DataTable